package com.project.blogapp.controllers;

import com.project.blogapp.payloads.ApiResponse;
import com.project.blogapp.payloads.CategoryResponseDto;
import com.project.blogapp.payloads.CategoryRequestDto;
import com.project.blogapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @PostMapping("")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid  @RequestBody CategoryRequestDto categoryDto){
        CategoryResponseDto createCat = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryResponseDto>(createCat, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryResponseDto> createCategory(@Valid @RequestBody CategoryRequestDto categoryResponseDto, @PathVariable Integer catId){
        CategoryResponseDto updateCat = categoryService.updateCategory(categoryResponseDto, catId);
        return new ResponseEntity<CategoryResponseDto>(updateCat, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
        categoryService.getCategoryById(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryResponseDto> getCategory(@PathVariable Integer catId){
        CategoryResponseDto categoryResponseDto = categoryService.getCategoryById(catId);
        return new ResponseEntity<CategoryResponseDto>(categoryResponseDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryResponseDto>> getCategories(){
        List<CategoryResponseDto> allCategories = categoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }
}
