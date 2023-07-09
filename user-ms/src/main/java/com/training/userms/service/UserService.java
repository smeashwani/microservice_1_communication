package com.training.userms.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.training.userms.entity.UserEntity;
import com.training.userms.model.DepartmentDto;
import com.training.userms.model.ResponseDto;
import com.training.userms.model.UserDto;
import com.training.userms.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserRepository userRepository;
	private WebClient webClient;


	public UserDto save(UserEntity user) {
		return mapToUser(userRepository.save(user));
	}

    public ResponseDto getUser(Long userId) {
    	ResponseDto responseDto = new ResponseDto();
        UserEntity user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

        DepartmentDto departmentDto = webClient.get()
                 .uri("http://localhost:8080/api/departments/" + user.getDepartmentId())
                         .retrieve()
                         .bodyToMono(DepartmentDto.class)
                         .block();
        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);

        return responseDto;
    }
	
	private UserDto mapToUser(UserEntity user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
