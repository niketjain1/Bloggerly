package com.project.blogapp.payloads;

import lombok.Data;

@Data
public class CreateUserDto {
    private String username;
    private String email;
    private String password;
    private String about;
}

