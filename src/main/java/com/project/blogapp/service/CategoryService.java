package com.project.blogapp.service;

import com.project.blogapp.payloads.CategoryResponseDto;
import com.project.blogapp.payloads.CategoryRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {

    //create
    CategoryResponseDto createCategory(CategoryRequestDto request);
    //update
    CategoryResponseDto updateCategory(CategoryRequestDto request, Integer categoryId);
    //delete
    public void deleteCategory(Integer categoryId);
    //get
    CategoryResponseDto getCategoryById(Integer categoryId);

    //get ALl
    List<CategoryResponseDto> getAllCategories();
}
