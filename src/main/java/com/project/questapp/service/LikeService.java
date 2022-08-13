package com.project.questapp.service;

import com.project.questapp.entities.Like;
import com.project.questapp.request.LikeCreateRequest;
import com.project.questapp.response.LikeResponse;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId);

    Like createOneLike(LikeCreateRequest request);

    Like getOneLikeById(Long likeId);

    void deleteOneLikeById(Long likeId);
}
