package com.project.blogapp.service;

import com.project.blogapp.payloads.PostResponseDto;
import com.project.blogapp.payloads.PostRequestDto;
import com.project.blogapp.payloads.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostResponseDto createPost(PostRequestDto postDto, Integer userId, Integer categoryId);

    PostResponseDto updatePost(PostResponseDto postResponseDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    PostResponseDto getPostById(Integer postId);

    List<PostResponseDto> getPostByCategory(Integer categoryId);

    List<PostResponseDto> getPostByUser(Integer userId);

    List<PostResponseDto> searchPosts(String keyword);
}
