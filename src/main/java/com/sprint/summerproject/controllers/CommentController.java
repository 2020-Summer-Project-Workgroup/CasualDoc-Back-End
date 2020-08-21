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

    @PostMapping("/file")
    public Comment addFileComment(String fileId, String reviewerId, String content) {
        return commentService.addCommentToFile(fileId, reviewerId, content);
    }

    @DeleteMapping("/file")
    public String deleteFileComment(String fileId, String commentId) {
        commentService.deleteCommentFromFile(fileId, commentId);
        return "Yes";
    }

}
