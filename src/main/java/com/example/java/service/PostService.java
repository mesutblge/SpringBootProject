package com.example.java.service;

import com.example.java.dto.PostCreateDto;
import com.example.java.dto.PostUpdateDto;
import com.example.java.entity.Post;
import com.example.java.entity.User;
import com.example.java.repository.PostRepository;
import com.example.java.response.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    public List<PostResponse> getAllPosts(Optional<Long> userId){
        List<Post> list;
        if(userId.isPresent())
            list = postRepository.findByUserId(userId.get());

        list = postRepository.findAll();
        return list.stream().map(p-> new PostResponse(p)).collect(Collectors.toList());
    }

    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createOnePost(PostCreateDto newPostDto) {
        User user = userService.getUserById(newPostDto.getUserId());
        if (user==null)
            return null;
        Post toSave=new Post();
        toSave.setId(newPostDto.getId());
        toSave.setText(newPostDto.getText());
        toSave.setTitle(newPostDto.getTitle());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    public Post updatePostById(Long postId, PostUpdateDto postUpdateDto) {

        Optional<Post> post=postRepository.findById(postId);
        if (post.isPresent()){
            Post toUpdate=post.get();
            toUpdate.setText(postUpdateDto.getText());
            toUpdate.setTitle(postUpdateDto.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    public void deletePostById(Long postId) {

        postRepository.deleteById(postId);

    }
}
