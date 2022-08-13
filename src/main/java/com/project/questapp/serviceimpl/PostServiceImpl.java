package com.project.questapp.serviceimpl;

import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.PostRepository;
import com.project.questapp.request.PostCreateRequest;
import com.project.questapp.request.PostUpdateRequest;
import com.project.questapp.response.LikeResponse;
import com.project.questapp.response.PostResponse;
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
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final LikeService likeService;

    @Override
    public List<PostResponse> getAllPost(Optional<Long> userId) {
        List<Post> list;
        if (userId.isPresent()) {
            list = postRepository.findByUserId(userId);
        } else {
            list = postRepository.findAll();
        }

        return list.stream().map(p -> {
            List<LikeResponse> likes = likeService.getAllLikesWithParam(Optional.ofNullable(null), Optional.of(p.getId()));
            return new PostResponse(p, likes);
        }).collect(Collectors.toList());
    }

    @Override
    public Post getOnePostById(long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public Post createPost(PostCreateRequest postCreateRequest) {
        User user = userService.getUserById(postCreateRequest.getUserId());
        if (user == null)
            return null;
        Post toSave = new Post();
        toSave.setId(postCreateRequest.getId());
        toSave.setTitle(postCreateRequest.getTitle());
        toSave.setText(postCreateRequest.getText());
        toSave.setUser(user);
        return postRepository.save(toSave);
    }

    @Override
    public Post updatePost(Long id, PostUpdateRequest postUpdateRequest) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            Post toUpdate = post.get();
            toUpdate.setText(postUpdateRequest.getText());
            toUpdate.setTitle(postUpdateRequest.getTitle());
            postRepository.save(toUpdate);
            return toUpdate;
        }
        return null;
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
