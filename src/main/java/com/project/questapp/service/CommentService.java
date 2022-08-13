package com.project.questapp.service;

import com.project.questapp.entities.Comment;
import com.project.questapp.request.CommentCreateRequest;
import com.project.questapp.request.CommentUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId);

    Comment getCommentById(Long id);

    Comment createComment(CommentCreateRequest request);

    Comment updateCommentById(Long id, CommentUpdateRequest request);

    void deleteCommentById(Long id);
}
