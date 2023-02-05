package com.project.blogapp.serviceImpl;

import com.project.blogapp.entities.CategoryEntity;
import com.project.blogapp.entities.PostEntity;
import com.project.blogapp.entities.UserEntity;
import com.project.blogapp.exceptions.ResourceNotFoundException;
import com.project.blogapp.payloads.CategoryDto;
import com.project.blogapp.payloads.PostDto;
import com.project.blogapp.payloads.PostResponse;
import com.project.blogapp.repositories.CategoryRepository;
import com.project.blogapp.repositories.PostRepository;
import com.project.blogapp.repositories.UserRepository;
import com.project.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
        CategoryEntity category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category", "categoryId", categoryId)
        );
        PostEntity post = modelMapper.map(postDto, PostEntity.class);
        post.setImageName("Default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        PostEntity savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "post id", postId)
        );
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());

        PostEntity updatedPost = postRepository.save(post);

        return modelMapper.map(updatedPost, PostDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "post id", postId)
        );
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy) {

        Pageable p = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).ascending());

        Page<PostEntity> pagePosts = postRepository.findAll(p);
        List<PostEntity> posts = pagePosts.getContent();

        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements((int) pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "post id", postId)
        );
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        CategoryEntity cat = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        List<PostEntity> posts = postRepository.findByCategory(cat);
        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        List<PostEntity> posts = postRepository.findByUser(user);
        List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtos;
    }

    @Override
    public List<PostEntity> searchPosts(String keyword) {
        return null;
    }
}