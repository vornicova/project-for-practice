package com.practice.demo.controller;

import com.practice.demo.dto.UserDto;
import com.practice.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Long createUser(@Valid @RequestBody UserDto dto) {
        return userService.create(dto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }
}
