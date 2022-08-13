package com.project.questapp.controllers;

import com.project.questapp.entities.Post;
import com.project.questapp.request.PostCreateRequest;
import com.project.questapp.request.PostUpdateRequest;
import com.project.questapp.response.PostResponse;
import com.project.questapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostResponse> getAllPost(@RequestParam Optional<Long> userId) {
        return postService.getAllPost(userId); //Optional olanda parametr gələdə bilər gəlmiyədə
    }

    @GetMapping("/{post_id}")
    public Post getOnePostById(@PathVariable("post_id") long id) {
        return postService.getOnePostById(id);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateRequest postCreateRequest) {
        return postService.createPost(postCreateRequest);
    }

    @PutMapping("/{post_id}")
    public Post updatePost(@PathVariable("post_id") Long id, @RequestBody PostUpdateRequest postUpdateRequest){
        return postService.updatePost(id,postUpdateRequest);
    }

    @DeleteMapping("/{post_id}")
    public void deletePost(@PathVariable("post_id") Long id){
        postService.deletePost(id);
    }
}
