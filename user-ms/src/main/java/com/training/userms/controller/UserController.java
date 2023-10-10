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
import com.training.userms.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
@Slf4j
public class UserController {

	private UserService userService;

	@PostMapping
	public ResponseEntity<ResponseDto> saveUser(@RequestBody UserEntity user) {
		log.info("saveUser - {}",user);
		ResponseDto savedUser = userService.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> getUser(@PathVariable("id") Long userId) {
		log.info("getUser - {}",userId);
		ResponseDto responseDto = userService.getUser(userId);
		return ResponseEntity.ok(responseDto);
	}
}
