package com.sprint.summerproject.services;

import com.sprint.summerproject.models.Comment;
import com.sprint.summerproject.models.File;
import com.sprint.summerproject.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final FileService fileService;

    public CommentService(CommentRepository commentRepository, FileService fileService) {
        this.commentRepository = commentRepository;
        this.fileService = fileService;
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public File addCommentToFile(String fileId, String reviewerId, String content) {
        File file = fileService.retrieveFileById(fileId);
        List<Comment> comments = file.getComments();
        Comment comment = new Comment(reviewerId, null, content, new Date());
        comment = commentRepository.save(comment);
        comments.add(comment);
        file.setComments(comments);
        fileService.writeFile(file);
        return file;
    }

    public void deleteCommentFromFile(String fileId, String commentId) {
        File file = fileService.retrieveFileById(fileId);
        List<Comment> comments = file.getComments();
        comments.removeIf(comment -> comment.getId().equals(commentId));
        file.setComments(comments);
        fileService.writeFile(file);
    }

}
