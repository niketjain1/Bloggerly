package com.project.blogapp.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private int id;
    private String username;
    private String email;
    private String token;
    private String about;
}
