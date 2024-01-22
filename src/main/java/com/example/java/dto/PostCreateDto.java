package com.example.java.dto;

import lombok.Data;

@Data
public class PostCreateDto {

    private Long id;
    private String text;
    private String title;
    private Long userId;

}
