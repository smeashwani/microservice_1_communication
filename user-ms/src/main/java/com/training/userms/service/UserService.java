package com.training.userms.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.shared.DepartmentDto;
import com.training.userms.entity.UserEntity;
import com.training.userms.model.ResponseDto;
import com.training.userms.model.UserDto;
import com.training.userms.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

	private UserRepository userRepository;
	private RestTemplate restTemplate;
	private KafkaTemplate<String, String> kafkaTemplate;
	private NewTopic topic;

	private Environment env;

	public UserEntity save(UserDto user) {
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		return userRepository.save(userEntity);
	}

	public void createDepartment(UserDto user) {
		DepartmentDto department = user.getDepartment();
		Message<DepartmentDto> message = MessageBuilder.withPayload(department)
				.setHeader(KafkaHeaders.TOPIC, topic.name()).build();
		log.info("Sending Kaffka Message: ------- {}", message);
		kafkaTemplate.send(message);
	}

	public ResponseDto getUser(Long userId) {
		ResponseDto responseDto = new ResponseDto();
		UserEntity user = userRepository.findById(userId).get();
		UserDto userDto = mapToUser(user);
		DepartmentDto departmentDto = null;
		try {
			ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity(
					env.getProperty("hostname.department") + "/api/departments/users/" + user.getId(),
					DepartmentDto.class);
			departmentDto = responseEntity.getBody();
		} catch (Exception e) {
			log.error("HTTP Request failed - {}", e.getMessage());
		}
		userDto.setDepartment(departmentDto);
		responseDto.setUser(userDto);

		return responseDto;
	}

	private UserDto mapToUser(UserEntity user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setEmail(user.getEmail());
		return userDto;
	}
}
