package com.project.blogapp.service;

import com.project.blogapp.entities.UserEntity;
import com.project.blogapp.exceptions.ResourceNotFoundException;
import com.project.blogapp.payloads.CreateUserDto;
import com.project.blogapp.payloads.LoginResponseDto;
import com.project.blogapp.payloads.LoginUserDto;
import com.project.blogapp.payloads.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(CreateUserDto request) throws ResourceNotFoundException;

    LoginResponseDto verifyUser(LoginUserDto request) throws ResourceNotFoundException;

    UserResponseDto getUserById(int userId) throws ResourceNotFoundException;
    void deleteUserById(int userId) throws ResourceNotFoundException;

    UserResponseDto updateUser(CreateUserDto userDto, int userID) throws ResourceNotFoundException;
}
