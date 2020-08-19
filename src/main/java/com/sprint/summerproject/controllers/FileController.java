package com.sprint.summerproject.controllers;

import com.sprint.summerproject.models.File;
import com.sprint.summerproject.services.FileService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("file")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/all")
    public List<File> findAllFiles() {
        return fileService.findAllFiles();
    }

    @GetMapping("/info")
    public File getFileInfo(String id) {
        return fileService.retrieveFileById(id);
    }

    @PostMapping("/user")
    public String uploadUserFile(String userId,
                                 String title,
                                 String content) {
        fileService.addDocToUser(userId, title, content);
        return "Yes";
    }

    @PutMapping("/user")
    public String updateUserFile(String userId,
                                 String fileId,
                                 String title,
                                 String content) {
        fileService.updateDocForUser(userId, fileId, title, content);
        return "Yes";
    }

    @DeleteMapping("/user")
    public String deleteUserFile(String userId, String fileId) {
        fileService.deleteDocFromUser(userId, fileId);
        return "Yes";
    }

    @GetMapping("/user/all")
    public List<File> getUserFiles(@RequestParam String userId) {
        return fileService.getUserFiles(userId);
    }

    @PutMapping("/user/favorite")
    public String updateUserFavoriteFiles(@RequestParam String userId,
                                          @RequestParam String fileId) {
        return fileService.updateUserFavoriteFiles(userId, fileId);
    }

    @GetMapping("/user/favorite/all")
    public List<File> UserFavoriteFiles(@RequestParam String userId) {
        return fileService.getUserFavoriteFiles(userId);
    }

}
