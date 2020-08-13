package com.sprint.summerproject.controllers;

import com.sprint.summerproject.models.Comment;
import com.sprint.summerproject.repositories.CommentRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentRepository commentRepository;

    public CommentController(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @GetMapping("/all")
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }
}
