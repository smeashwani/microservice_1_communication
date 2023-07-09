package com.training.userms.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.training.userms.model.DepartmentDto;

@FeignClient(value = "DEPARTMENT-SERVICE", url = "http://localhost:8080")
public interface APIClient {
	
	@GetMapping(value = "/api/departments/{id}")
	DepartmentDto getDepartmentById(@PathVariable("id") String departmentId);
}