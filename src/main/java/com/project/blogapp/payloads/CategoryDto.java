package com.project.blogapp.payloads;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CategoryDto {
    private int id;
    @NotBlank
    @Size(min = 2, message = "Minimum size of tile should be 2")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10, message = "Minimum size of tile should be 10")
    private String categoryDescription;
}
