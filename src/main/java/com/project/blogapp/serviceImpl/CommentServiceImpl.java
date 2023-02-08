package com.project.blogapp.serviceImpl;

import com.project.blogapp.entities.CommentEntity;
import com.project.blogapp.entities.PostEntity;
import com.project.blogapp.exceptions.ResourceNotFoundException;
import com.project.blogapp.payloads.CommentDto;
import com.project.blogapp.payloads.PostResponse;
import com.project.blogapp.repositories.CommentRepository;
import com.project.blogapp.repositories.PostRepository;
import com.project.blogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postID) {
        PostEntity post = postRepository.findById(postID).orElseThrow(
                () -> new ResourceNotFoundException("Post", "post id", postID)
        );
        CommentEntity comment = modelMapper.map(commentDto, CommentEntity.class);
        comment.setPost(post);
        CommentEntity savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment", "comment id", commentId)
        );
        commentRepository.delete(comment);
    }
}
