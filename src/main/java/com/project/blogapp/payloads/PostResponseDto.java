package com.project.blogapp.payloads;

import com.project.blogapp.entities.CommentEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

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

    private List<CommentDto> comments = new ArrayList<>();
}
