package com.project.blogapp.payloads;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class PostResponseDto {

    private Integer pid;
    private String title;
    private String content;

    private String imageName;

    private Date addedDate;

    private CategoryResponseDto category;

    private UserResponseDto user;

    private Set<CommentDto> comments = new HashSet<>();
}
