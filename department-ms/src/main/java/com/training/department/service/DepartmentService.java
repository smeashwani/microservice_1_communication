package com.training.department.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.training.department.entity.Department;
import com.training.department.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {

    private DepartmentRepository departmentRepository;
    private Environment environment;

    public Department saveDepartment(Department department) {
         Department save = departmentRepository.save(department);
         save.setPort(environment.getProperty("local.server.port"));
         return save;
    }

    public Department getDepartmentById(Long departmentId) {
    	Department department = departmentRepository.findById(departmentId).get();
    	department.setPort(environment.getProperty("local.server.port"));
    	return department;
    }
}