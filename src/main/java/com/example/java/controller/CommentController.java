package com.example.java.controller;

import com.example.java.dto.CommentCreateDto;
import com.example.java.dto.CommentUpdateDto;
import com.example.java.entity.Comment;
import com.example.java.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAllComment")
    public List<Comment> getAllComment(@RequestParam Optional<Long> userId,
                                       @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId,postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentById(commentId);
    }

    @PostMapping("/createComment")
    public Comment createComment(@RequestBody CommentCreateDto dto){

        return commentService.createComment(dto);
    }
    @PutMapping("/updateComment/{commentId}")
    public Comment updateComment(@PathVariable Long commentId,
                                 @RequestBody CommentUpdateDto dto){
        return commentService.updateComment(commentId,dto);
    }
    @DeleteMapping("/deleteComment/{commentId}")
    public Comment deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
        return null;
    }

}
