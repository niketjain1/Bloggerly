package com.project.blogapp.users.dtos;

import lombok.Data;

@Data
public class UserResponseDto {

    private int id;

    private String username;
    private String email;
    private String token;

}