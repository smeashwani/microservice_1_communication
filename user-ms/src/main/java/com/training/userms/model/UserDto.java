package com.training.userms.model;

import com.training.shared.DepartmentDto;

import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private DepartmentDto department;
}