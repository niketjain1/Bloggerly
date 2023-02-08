package com.project.blogapp.service;

import com.project.blogapp.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, Integer postID);
    void deleteComment(Integer commentId);
}
