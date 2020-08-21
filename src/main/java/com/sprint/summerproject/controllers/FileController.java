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
    public File getFileInfo(String fileId) {
        return fileService.retrieveFileById(fileId);
    }

    @PostMapping("/user")
    public File uploadUserFile(String userId,
                               String title,
                               String content) {
        return fileService.addDocToUser(userId, title, content);
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
        return fileService.getUserActiveFiles(userId);
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

    @PutMapping("/user/recycle")
    public String updateUserRecycleBin(String fileId) {
        fileService.updateUserFileStatus(fileId);
        return "Yes";
    }

    @GetMapping("/user/recycle/all")
    public List<File> getUserRecycleBin(String userId) {
        return fileService.getUserTrashedFiles(userId);
    }

    @PostMapping("/user/recent")
    public String updateUserRecentFiles(String userId, String fileId) {
        fileService.updateUserRecentFiles(userId, fileId);
        return "Yes";
    }

    @GetMapping("/user/recent/all")
    public List<File> getUserRecentFiles(String userId) {
        return fileService.getUserRecentFiles(userId);
    }

}
