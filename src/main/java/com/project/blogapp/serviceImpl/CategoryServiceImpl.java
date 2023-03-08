package com.project.blogapp.serviceImpl;

import com.project.blogapp.entities.CategoryEntity;
import com.project.blogapp.exceptions.ResourceNotFoundException;
import com.project.blogapp.payloads.CategoryResponseDto;
import com.project.blogapp.payloads.CategoryRequestDto;
import com.project.blogapp.repositories.CategoryRepository;
import com.project.blogapp.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto request) {
        CategoryEntity cat = modelMapper.map(request, CategoryEntity.class);
        CategoryEntity savedCat = categoryRepository.save(cat);

        return modelMapper.map(savedCat, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto updateCategory(CategoryRequestDto request, Integer categoryId) {
        CategoryEntity cat = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        cat.setCategoryTitle(request.getCategoryTitle());
        cat.setCategoryDescription(request.getCategoryDescription());
        CategoryEntity updatedCat = categoryRepository.save(cat);
        return modelMapper.map(updatedCat, CategoryResponseDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        CategoryEntity cat = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
        categoryRepository.delete(cat);
    }

    @Override
    public CategoryResponseDto getCategoryById(Integer categoryId) {
        CategoryEntity cat = categoryRepository.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));

        return modelMapper.map(cat, CategoryResponseDto.class);
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<CategoryEntity> allCat = categoryRepository.findAll();
        List<CategoryResponseDto> response = allCat.stream().map((cat) -> modelMapper.map(cat, CategoryResponseDto.class)).collect(Collectors.toList());
        return response;
    }
}
