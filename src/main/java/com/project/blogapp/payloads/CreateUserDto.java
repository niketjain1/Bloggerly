package com.project.blogapp.payloads;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
public class CreateUserDto {
    @NotEmpty
    @Size(min = 4, message = "Username must be minimum of 4 characters !!")
    private String username;
    @Email(message = "Email address is not valid !!")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "Password must be minimum of 3 characters and maximum of 10 characters !!")
    private String password;
    @NotEmpty
    private String about;

    private Set<RoleDto> roles = new HashSet<>();

}

