package com.sprint.summerproject.controllers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sprint.summerproject.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("file")
public class FileController {

    private final FileService fileService;
    @Value("${files.path}")
    private String filePath;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/all")
    public List<com.sprint.summerproject.models.File> findAllFiles() {
        return fileService.findAllFiles();
    }

    @PostMapping("/user")
    public String uploadUserFile(String userId,
                                 String title,
                                 MultipartFile file) throws IOException {
        // Write this file to server's disk
        String extensionName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + extensionName;
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath + fileName)));

        // Add this document to this user's file list
        fileService.addDocToUser(userId, fileName, title);
        return "Document added to user's file list successfully:\t" + fileName;
    }

    @PutMapping("/user")
    public String updateUserFile(String userId,
                                 String fileName,
                                 MultipartFile file) throws IOException {
        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(new File(filePath + fileName)));
        fileService.updateDocForUser(userId, fileName);
        return "Document updated successfully:\t" + fileName;
    }

    @GetMapping("/user")
    public String getUserFile(String fileName) throws IOException {
        return fileService.getDocOfUser(fileName);
    }

    @GetMapping("/user/all")
    public List<com.sprint.summerproject.models.File> getUserFiles(@RequestParam String userId) {
        return fileService.getUserFiles(userId);
    }

    @PutMapping("/user/favorite")
    public String updateUserFavoriteFiles(@RequestParam String userId,
                                          @RequestParam String fileName) {
        return fileService.updateUserFavoriteFiles(userId, fileName);
    }

    @GetMapping("/user/favorite")
    public List<com.sprint.summerproject.models.File> UserFavoriteFiles(@RequestParam String userId) {
        return fileService.getUserFavoriteFiles(userId);
    }

}
