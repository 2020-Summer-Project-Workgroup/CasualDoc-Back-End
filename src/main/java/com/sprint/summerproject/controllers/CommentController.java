package com.sprint.summerproject.controllers;

import com.sprint.summerproject.models.Comment;
import com.sprint.summerproject.models.File;
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

//    @GetMapping("/all")
//    public List<Comment> getAll() {
//        return commentService.findAllComments();
//    }

    @PostMapping("/file")
    public File addFileComment(String fileId, String reviewerId, String content) {
        return commentService.addCommentToFile(fileId, reviewerId, content);
    }

    @DeleteMapping("/file")
    public File deleteFileComment(String fileId, String commentId) {
        return commentService.deleteCommentFromFile(fileId, commentId);
    }

}
