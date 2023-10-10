package com.training.department.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.department.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

	Optional<Department> findByUserId(Long userId);
}