package com.project.blogapp.payloads;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryResponseDto {
    private int id;

    private String categoryTitle;

    private String categoryDescription;
}
