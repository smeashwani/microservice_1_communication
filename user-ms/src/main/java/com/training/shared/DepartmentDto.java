package com.training.shared;

import lombok.Data;

@Data
public class DepartmentDto {
	private Long id;
	private String departmentName;
	private String departmentAddress;
	private String departmentCode;
	private Long userId;
}