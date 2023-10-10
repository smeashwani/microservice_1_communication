package com.training.userms.service;

import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.userms.entity.UserEntity;
import com.training.userms.model.DepartmentDto;
import com.training.userms.model.ResponseDto;
import com.training.userms.model.UserDto;
import com.training.userms.repository.UserRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

	@NonNull private Environment env;
	@NonNull private UserRepository userRepository;
	@NonNull private RestTemplate restTemplate;
	@NonNull private int count=0;
	
	public ResponseDto save(UserEntity user) {
		UserEntity userEntity = userRepository.save(user);
		return getUser(userEntity.getId());
	}

	
	@CircuitBreaker(name = "user-resiliency", fallbackMethod = "fallbackGetUser")
	public ResponseDto getUser(Long userId) {
		count++;
		log.info("Retry -> {}", count);
		if(count <= 5) {
			throw new RuntimeException("Problem in fetching details");
		}
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
	
	
	public ResponseDto fallbackGetUser(Long userId,Exception ex) {
		log.info("Fallback-GetUser:  Fetch Department By UserId - {} ",userId);
		log.info("Fallback-GetUser:  Exception  - {} ",ex.getMessage());
		ResponseDto responseDto = new ResponseDto();
		UserEntity user = userRepository.findById(userId).get();
		UserDto userDto = mapToUser(user);

		ResponseEntity<DepartmentDto> responseEntity = restTemplate
				.getForEntity(env.getProperty("hostname.department") + "/api/departments/users/" + userId, DepartmentDto.class);

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