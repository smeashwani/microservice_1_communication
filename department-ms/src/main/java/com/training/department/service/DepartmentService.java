package com.training.department.service;

import org.springframework.stereotype.Service;

import com.training.department.entity.Department;
import com.training.department.repository.DepartmentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department getDepartmentById(Long departmentId) {
        return departmentRepository.findById(departmentId).get();
    }
    
    public Department getDepartmentByUserId(Long userId) {
        return departmentRepository.findByUserId(userId).get();
    }
}