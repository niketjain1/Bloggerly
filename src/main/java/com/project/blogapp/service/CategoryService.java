package com.project.blogapp.service;

import com.project.blogapp.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto request);
    //update
    CategoryDto updateCategory(CategoryDto request, Integer categoryId);
    //delete
    public void deleteCategory(Integer categoryId);
    //get
    CategoryDto getCategoryById(Integer categoryId);

    //get ALl
    List<CategoryDto> getAllCategories();
}
