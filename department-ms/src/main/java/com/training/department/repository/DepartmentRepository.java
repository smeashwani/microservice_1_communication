package com.training.department.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.department.entity.DepartmentEntity;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

	Optional<DepartmentEntity> findByUserId(Long departmentId);

}