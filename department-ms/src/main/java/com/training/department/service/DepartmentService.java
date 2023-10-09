package com.training.department.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.training.department.entity.DepartmentEntity;
import com.training.department.repository.DepartmentRepository;
import com.training.shared.DepartmentDto;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class DepartmentService {

	private DepartmentRepository departmentRepository;

	public DepartmentEntity save(DepartmentDto department) {
		DepartmentEntity dep = new DepartmentEntity();
		BeanUtils.copyProperties(department, dep);
		return departmentRepository.save(dep);
	}

	public List<DepartmentEntity> getAll() {
		return departmentRepository.findAll();
	}

	public DepartmentEntity getDepartmentById(Long departmentId) {
		log.info("getDepartmentById ={}", departmentId);
		return departmentRepository.findById(departmentId).get();
	}

	public DepartmentEntity getDepartmentByUserId(Long userId) {
		log.info("getDepartmentByUserId ={}", userId);
		return departmentRepository.findByUserId(userId).get();
	}

	@KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(DepartmentDto department) {
		log.info("Deparment received in department service => {}", department);
		save(department);
	}
}