package com.sprint.summerproject.services;

import com.sprint.summerproject.models.Comment;
import com.sprint.summerproject.models.File;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private FileService fileService;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, FileService fileService, UserService userService) {
        this.commentRepository = commentRepository;
        this.fileService = fileService;
        this.userService = userService;
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public void addCommentToUserFile(String revieweeId, String fileName, String reviewerId, String content) {
        Comment comment = new Comment(reviewerId, null, content, new Date());
        User user = userService.retrieveUserById(revieweeId);
        List<File> userFiles = user.getFiles();
        for (File file : userFiles) {
            if (file.getFileName().equals(fileName)) {
                List<Comment> comments = file.getComments();
                comments.add(comment);
                commentRepository.save(comment);
            }
        }
        userService.writeUser(user);
    }

    public void deleteCommentFromUserFile(String revieweeId, String fileName, String commentId) {
        User user = userService.retrieveUserById(revieweeId);
        List<File> userFiles = user.getFiles();
        for (File file : userFiles) {
            if (file.getFileName().equals(fileName)) {
                List<Comment> comments = file.getComments();
                comments.removeIf(comment -> comment.getId().equals(commentId));
            }
        }
        userService.writeUser(user);
    }

}
