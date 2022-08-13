package com.project.questapp.serviceimpl;

import com.project.questapp.entities.Like;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.LikeRepository;
import com.project.questapp.request.LikeCreateRequest;
import com.project.questapp.response.LikeResponse;
import com.project.questapp.service.LikeService;
import com.project.questapp.service.PostService;
import com.project.questapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final PostService postService;
    private final UserService userService;

    @Override
    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> likeList;
        if (userId.isPresent() & postId.isPresent()) {
            likeList = likeRepository.findByUserIdAndPostId(userId, postId);
        } else if (userId.isPresent()) {
            likeList = likeRepository.findByUserId(userId);
        } else if (postId.isPresent()) {
            likeList = likeRepository.findByPostId(postId);
        } else
            likeList = likeRepository.findAll();
        return likeList.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    @Override
    public Like createOneLike(LikeCreateRequest request) {
        User user = userService.getUserById(request.getUserId());
        Post post = postService.getOnePostById(request.getPostId());
        if (user != null && post != null) {
            Like likeToSave = new Like();
            likeToSave.setId(request.getId());
            likeToSave.setPost(post);
            likeToSave.setUser(user);
            return likeRepository.save(likeToSave);
        } else
            return null;
    }

    @Override
    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    @Override
    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
