package com.practice.demo.repository;

import com.practice.demo.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    UserEntity save(UserEntity user);
    Optional<UserEntity> findById(Long id);
    List<UserEntity> findAll();
}
