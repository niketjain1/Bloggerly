package com.project.blogapp.controllers;

import com.project.blogapp.config.AppConstants;
import com.project.blogapp.payloads.ApiResponse;
import com.project.blogapp.payloads.PostResponseDto;
import com.project.blogapp.payloads.PostRequestDto;
import com.project.blogapp.payloads.PostResponse;
import com.project.blogapp.service.FileService;
import com.project.blogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostResponseDto> createPost(
            @RequestBody PostRequestDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ){
        PostResponseDto createdPost = postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostResponseDto>(createdPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostResponseDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostResponseDto> posts = postService.getPostByUser(userId);
        System.out.println(posts);
        return new ResponseEntity<List<PostResponseDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostResponseDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostResponseDto> posts = postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostResponseDto>>(posts, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir
    ){
        PostResponse posts = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(posts, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Integer postId){
        PostResponseDto post = postService.getPostById(postId);

        return new ResponseEntity<PostResponseDto>(post, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        return new ApiResponse("Post is successfully deleted", true);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@RequestBody PostResponseDto postResponseDto, @PathVariable Integer postId){
        PostResponseDto updatePost = postService.updatePost(postResponseDto, postId);
        return new ResponseEntity<PostResponseDto>(updatePost, HttpStatus.OK);
    }

    @GetMapping("/search/{keywords}")
    public ResponseEntity<List<PostResponseDto>> searchPostByTitle(
            @PathVariable("keywords") String keywords
    ){
        List<PostResponseDto> result = postService.searchPosts(keywords);
        return new ResponseEntity<List<PostResponseDto>>(result, HttpStatus.OK);
    }

    // Post image

    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostResponseDto> uploadImage(
            @PathVariable("postId") Integer postId,
            @RequestParam("image") MultipartFile image) throws IOException {

        PostResponseDto postResponseDto = postService.getPostById(postId);
        String fileName = this.fileService.uploadImage(path, image);

        postResponseDto.setImageName(fileName);
        PostResponseDto updatedPost = postService.updatePost(postResponseDto, postId);
        return new ResponseEntity<PostResponseDto>(updatedPost, HttpStatus.OK);
    }

    // Method to serve files
    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.fileService.getResource(path ,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }
}
