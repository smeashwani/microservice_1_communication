package com.training.department.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.department.entity.Department;
import com.training.department.service.DepartmentService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
@Slf4j
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
    	log.info("Save Department:- {}", department);
        Department savedDepartment = departmentService.saveDepartment(department);
        log.info("Saved Department details:- {}", savedDepartment);
        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long departmentId){
    	log.info("Get Department:- {}", departmentId);
        Department department = departmentService.getDepartmentById(departmentId);
        log.info("Fetching Department:- {}", department);
        return ResponseEntity.ok(department);
    }
}