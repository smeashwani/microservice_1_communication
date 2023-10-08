package com.training.userms.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.userms.entity.UserEntity;
import com.training.userms.model.DepartmentDto;
import com.training.userms.model.ResponseDto;
import com.training.userms.model.UserDto;
import com.training.userms.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

	private Environment env;
	private UserRepository userRepository;
	private RestTemplate restTemplate;

	public ResponseDto save(UserEntity user) {
		UserEntity userEntity = userRepository.save(user);
		return getUser(userEntity.getId());
	}

	public ResponseDto getUser(Long userId) {
		ResponseDto responseDto = new ResponseDto();
		UserEntity user = userRepository.findById(userId).get();
		UserDto userDto = mapToUser(user);

		ResponseEntity<DepartmentDto> responseEntity = restTemplate
				.getForEntity(env.getProperty("hostname.department") + "/api/departments/" + user.getDepartmentId(), DepartmentDto.class);

		DepartmentDto departmentDto = responseEntity.getBody();

		log.info("Deparment Service Response code: {}", responseEntity.getStatusCode());
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