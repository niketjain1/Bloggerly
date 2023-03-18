package com.project.blogapp.controllers;

import com.project.blogapp.payloads.ApiResponse;
import com.project.blogapp.payloads.CommentDto;
import com.project.blogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Integer postId){
        CommentDto comment = commentService.createComment(commentDto, postId);
        return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<ApiResponse> deletComment(@PathVariable("commentId") Integer cid){
        commentService.deleteComment(cid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
    }
}
