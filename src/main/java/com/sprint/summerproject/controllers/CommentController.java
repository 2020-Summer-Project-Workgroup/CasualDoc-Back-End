package com.sprint.summerproject.controllers;

import com.sprint.summerproject.models.Comment;
import com.sprint.summerproject.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public List<Comment> getAll() {
        return commentService.findAllComments();
    }

    @PutMapping("/user/file")
    public String addFileComment(String revieweeId, String fileName, String reviewerId, String content) {
        commentService.addCommentToUserFile(revieweeId, fileName, reviewerId, content);
        return "Yes";
    }

    @DeleteMapping("/user/file")
    public String deleteFileComment(String revieweeId, String fileName, String commentId) {
        commentService.deleteCommentFromUserFile(revieweeId, fileName, commentId);
        return "Yes";
    }

}
