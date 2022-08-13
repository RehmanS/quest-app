package com.project.questapp.controllers;

import com.project.questapp.entities.Comment;
import com.project.questapp.request.CommentCreateRequest;
import com.project.questapp.request.CommentUpdateRequest;
import com.project.questapp.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable("commentId") Long id) {
        return commentService.getCommentById(id);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest request) {
        return commentService.createComment(request);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable("commentId") Long id, @RequestBody CommentUpdateRequest request){
        return commentService.updateCommentById(id, request);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable("commentId") Long id){
        commentService.deleteCommentById(id);
    }
}
