package com.training.department.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.department.entity.DepartmentEntity;
import com.training.department.service.DepartmentService;
import com.training.shared.DepartmentDto;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
public class DepartmentController {

	private DepartmentService departmentService;

	@PostMapping
	public ResponseEntity<DepartmentEntity> save(@RequestBody DepartmentDto department) {
		DepartmentEntity savedDepartment = departmentService.save(department);
		return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable("id") Long departmentId) {
		DepartmentEntity department = departmentService.getDepartmentById(departmentId);
		return ResponseEntity.ok(department);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<DepartmentEntity> getDepartmentByUserId(@PathVariable("id") Long userId) {
		DepartmentEntity department = departmentService.getDepartmentByUserId(userId);
		return ResponseEntity.ok(department);
	}

	@GetMapping()
	public ResponseEntity<List<DepartmentEntity>> getAll() {
		List<DepartmentEntity> all = departmentService.getAll();
		return ResponseEntity.ok(all);
	}
}