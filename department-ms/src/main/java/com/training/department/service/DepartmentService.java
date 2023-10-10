package com.training.department.service;

import org.springframework.stereotype.Service;

import com.training.department.entity.Department;
import com.training.department.repository.DepartmentRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
    	log.info("Save Department:- {}", department);
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long departmentId) {
    	log.info("Get DepartmentById:- {}", departmentId);
        return departmentRepository.findById(departmentId).get();
    }
}