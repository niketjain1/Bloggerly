package com.project.blogapp.payloads;

import com.project.blogapp.entities.CategoryEntity;
import com.project.blogapp.entities.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostDto {
    private String title;
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryDto category;

    private UserResponseDto user;
}
