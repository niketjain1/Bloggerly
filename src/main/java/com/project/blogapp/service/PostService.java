package com.project.blogapp.service;

import com.project.blogapp.entities.PostEntity;
import com.project.blogapp.payloads.PostDto;
import com.project.blogapp.payloads.PostResponse;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    PostDto updatePost(PostDto postDto, Integer postId);

    void deletePost(Integer postId);

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy);

    PostDto getPostById(Integer postId);

    List<PostDto> getPostByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostEntity> searchPosts(String keyword);
}
