package com.project.blogapp.controllers;

import com.project.blogapp.payloads.ApiResponse;
import com.project.blogapp.payloads.CategoryDto;
import com.project.blogapp.service.CategoryService;
import lombok.Getter;
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
    public ResponseEntity<CategoryDto> createCategory(@Valid  @RequestBody CategoryDto categoryDto){
        CategoryDto createCat = categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(createCat, HttpStatus.CREATED);
    }

    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId){
        CategoryDto updateCat = categoryService.updateCategory(categoryDto, catId);
        return new ResponseEntity<CategoryDto>(updateCat, HttpStatus.OK);
    }

    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId){
        categoryService.getCategoryById(catId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId){
        CategoryDto categoryDto = categoryService.getCategoryById(catId);
        return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }
}
