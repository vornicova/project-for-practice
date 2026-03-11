package com.practice.demo.service;

import com.practice.demo.dto.UserDto;
import com.practice.demo.entity.UserEntity;
import com.practice.demo.mapper.UserMapper;
import com.practice.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Long create(UserDto dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("ID must be null when creating a new user");
        }

        UserEntity entity = userMapper.toEntity(dto);
        UserEntity savedUser = userRepository.save(entity);
        return savedUser.getId();
    }

    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto findById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return userMapper.toDto(user);
    }
}