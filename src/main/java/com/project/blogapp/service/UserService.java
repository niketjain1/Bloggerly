package com.project.blogapp.service;

import com.project.blogapp.entities.UserEntity;
import com.project.blogapp.exceptions.ResourceNotFoundException;
import com.project.blogapp.payloads.CreateUserDto;
import com.project.blogapp.payloads.LoginUserDto;
import com.project.blogapp.payloads.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(CreateUserDto request);

    UserResponseDto verifyUser(LoginUserDto request) throws ResourceNotFoundException;

    void deleteUserById(int userId) throws ResourceNotFoundException;
}
