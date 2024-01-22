package com.example.java.dto;

import lombok.Data;

@Data
public class CommentCreateDto {
    private Long id;
    private Long userId;
    private Long postId;
    private String text;
}
