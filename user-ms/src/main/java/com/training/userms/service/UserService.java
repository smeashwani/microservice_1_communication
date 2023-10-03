package com.training.userms.service;

import org.springframework.stereotype.Service;

import com.training.userms.config.APIClient;
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
	private APIClient apiClient;

	public ResponseDto save(UserEntity user) {
		UserEntity userEntity = userRepository.save(user);
		return getUser(userEntity.getId());
	}

    public ResponseDto getUser(Long userId) {
    	ResponseDto responseDto = new ResponseDto();
        UserEntity user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

        DepartmentDto departmentDto = apiClient.getDepartmentById(user.getDepartmentId());
        userDto.setDepartment(departmentDto);
        responseDto.setUser(userDto);

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
