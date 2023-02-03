package com.project.blogapp.payloads;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LoginUserDto {
    @NotEmpty
    @Size(min = 4, message = "Username must be minimum of 4 characters !!")
    private String username;
    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be minimum of 3 characters and maximum of 10 characters !!")
    private String password;
}
