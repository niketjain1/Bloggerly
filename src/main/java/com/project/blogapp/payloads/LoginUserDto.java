package com.project.blogapp.payloads;

import lombok.Data;

@Data
public class LoginUserDto {
    private String username;
    private String password;
}
