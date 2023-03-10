package com.project.blogapp.serviceImpl;

import com.project.blogapp.entities.CategoryEntity;
import com.project.blogapp.entities.PostEntity;
import com.project.blogapp.entities.UserEntity;
import com.project.blogapp.exceptions.ResourceNotFoundException;
import com.project.blogapp.payloads.PostResponseDto;
import com.project.blogapp.payloads.PostRequestDto;
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
    public PostResponseDto createPost(PostRequestDto postDto, Integer userId, Integer categoryId) {
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
        return modelMapper.map(savedPost, PostResponseDto.class);
    }

    @Override
    public PostResponseDto updatePost(PostResponseDto postResponseDto, Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "post id", postId)
        );
        post.setTitle(postResponseDto.getTitle());
        post.setContent(postResponseDto.getContent());
        post.setImageName(postResponseDto.getImageName());

        PostEntity updatedPost = postRepository.save(post);

        return modelMapper.map(updatedPost, PostResponseDto.class);
    }

    @Override
    public void deletePost(Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "post id", postId)
        );
        postRepository.delete(post);
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (sortDir.equalsIgnoreCase("asc"))? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Pageable p = PageRequest.of(pageNumber, pageSize, sort);

        Page<PostEntity> pagePosts = postRepository.findAll(p);
        List<PostEntity> posts = pagePosts.getContent();

        List<PostResponseDto> postResponseDtos = posts.stream().map((post) -> modelMapper.map(post, PostResponseDto.class)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postResponseDtos);
        postResponse.setPageNumber(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements((int) pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLastPage(pagePosts.isLast());
        return postResponse;
    }

    @Override
    public PostResponseDto getPostById(Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "post id", postId)
        );

        PostResponseDto postResponseDto = modelMapper.map(post, PostResponseDto.class);

        return postResponseDto;
    }

    @Override
    public List<PostResponseDto> getPostByCategory(Integer categoryId) {
        CategoryEntity cat = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        List<PostEntity> posts = postRepository.findByCategory(cat);
        List<PostResponseDto> postResponseDtos = posts.stream().map((post) -> modelMapper.map(post, PostResponseDto.class)).collect(Collectors.toList());
        return postResponseDtos;
    }

    @Override
    public List<PostResponseDto> getPostByUser(Integer userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        List<PostEntity> posts = postRepository.findByUser(user);
        List<PostResponseDto> postResponseDtos = posts.stream().map((post) -> modelMapper.map(post, PostResponseDto.class)).collect(Collectors.toList());
        return postResponseDtos;
    }

    @Override
    public List<PostResponseDto> searchPosts(String keyword) {
        List<PostEntity> posts = postRepository.findByTitleContaining(keyword);
        List<PostResponseDto> postResponseDtos = posts.stream().map((post) -> modelMapper.map(post, PostResponseDto.class)).collect(Collectors.toList());

        return postResponseDtos;
    }
}