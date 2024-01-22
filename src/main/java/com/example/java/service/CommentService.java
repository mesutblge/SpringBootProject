package com.example.java.service;

import com.example.java.dto.CommentCreateDto;
import com.example.java.dto.CommentUpdateDto;
import com.example.java.entity.Comment;
import com.example.java.entity.Post;
import com.example.java.entity.User;
import com.example.java.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {

        if (userId.isPresent() && postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId.get());
        }else
            return commentRepository.findAll();
    }

    public Comment getOneCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateDto dto) {

        User user=userService.getUserById(dto.getUserId());
        Post post=postService.getPostById(dto.getPostId());
        if (user != null && post != null){
            Comment commentToSave=new Comment();
            commentToSave.setId(dto.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(dto.getText());
            return commentRepository.save(commentToSave);
        }else {
            return null;
        }
    }

    public Comment updateComment(Long commentId, CommentUpdateDto dto) {
        Optional<Comment> comment=commentRepository.findById(commentId);
        if (comment.isPresent()){
            Comment commentToUpdate=comment.get();
            commentToUpdate.setText(dto.getText());
            commentRepository.save(commentToUpdate);
        }else
            return null;
        return null;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
