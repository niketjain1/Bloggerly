package com.project.blogapp.payloads;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserResponseDto {

    private int id;

    private String username;
    private String email;
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}