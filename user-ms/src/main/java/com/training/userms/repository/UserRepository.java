package com.training.userms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.userms.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

}