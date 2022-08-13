package com.project.questapp.serviceimpl;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.CommentRepository;
import com.project.questapp.request.CommentCreateRequest;
import com.project.questapp.request.CommentUpdateRequest;
import com.project.questapp.service.CommentService;
import com.project.questapp.service.PostService;
import com.project.questapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    @Override
    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() & postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId, postId);
        } else if (userId.isPresent()) {
            return commentRepository.findByUserId(userId);
        } else if (postId.isPresent()) {
            return commentRepository.findByPostId(postId);
        } else
            return commentRepository.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment createComment(CommentCreateRequest request) {
        Post post = postService.getOnePostById(request.getPostId());
        User user = userService.getUserById(request.getUserId());
        if (user != null & post != null) {
            Comment commentToSave = new Comment();
            commentToSave.setId(request.getId());
            commentToSave.setPost(post);
            commentToSave.setUser(user);
            commentToSave.setText(request.getText());
            return commentRepository.save(commentToSave);
        } else
            return null;
    }

    @Override
    public Comment updateCommentById(Long id, CommentUpdateRequest request) {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(request.getText());
            return commentRepository.save(commentToUpdate);
        } else return null;

    }

    @Override
    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

}
