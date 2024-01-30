package com.example.java.controller;

import com.example.java.dto.PostCreateDto;
import com.example.java.dto.PostUpdateDto;
import com.example.java.entity.Post;
import com.example.java.response.PostResponse;
import com.example.java.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAllPosts")
    public List<PostResponse> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @PostMapping("/createPost")
    public Post createOnePost(@RequestBody PostCreateDto newPostDto){
        return postService.createOnePost(newPostDto);
    }

    @GetMapping("/{postId}")
    public Post getOnePost(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PutMapping("/updatePost")
    public Post updatePost(@PathVariable Long postId,
                           @RequestBody PostUpdateDto postUpdateDto){
        return postService.updatePostById(postId,postUpdateDto);
    }
    @DeleteMapping("/deletePost")
    public void deletePost(@PathVariable Long postId){
       postService.deletePostById(postId);
    }

}
