package com.training.userms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.userms.entity.UserEntity;
import com.training.userms.model.ResponseDto;
import com.training.userms.model.UserDto;
import com.training.userms.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {

	private UserService userService;

	@PostMapping
	public ResponseEntity<ResponseDto> saveUser(@RequestBody UserDto user) {
		UserEntity userEntity = userService.save(user);
		user.getDepartment().setUserId(userEntity.getId());
		userService.createDepartment(user);
		ResponseDto savedUser = userService.getUser(userEntity.getId());
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId) {
		ResponseDto responseDto = userService.getUser(userId);
		return ResponseEntity.ok(responseDto);
	}
}
