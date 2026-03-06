package com.practice.demo.mapper;

import com.practice.demo.dto.UserDto;
import com.practice.demo.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(UserDto dto) {
        UserEntity user = new UserEntity();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setIsActive(dto.getIsActive());
        user.setEmail(dto.getEmail());
        return user;
    }

    public UserDto toDto(UserEntity user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setIsActive(user.getIsActive());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}