package com.project.questapp.service;

import com.project.questapp.entities.Post;
import com.project.questapp.request.PostCreateRequest;
import com.project.questapp.request.PostUpdateRequest;
import com.project.questapp.response.PostResponse;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<PostResponse> getAllPost(Optional<Long> userId);

    Post getOnePostById(long id);

    Post createPost(PostCreateRequest postCreateRequest);

    Post updatePost(Long id, PostUpdateRequest postUpdateRequest);

    void deletePost(Long id);
}
