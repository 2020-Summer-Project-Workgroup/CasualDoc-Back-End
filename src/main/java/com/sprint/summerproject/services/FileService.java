package com.sprint.summerproject.services;

import com.sprint.summerproject.models.File;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.FileRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final UserService userService;

    public FileService(FileRepository fileRepository, UserService userService) {
        this.fileRepository = fileRepository;
        this.userService = userService;
    }

    public List<File> findAllFiles() {
        return fileRepository.findAll();
    }

    public void addDocToUser(String userId, String fileName, String title) {
        User user = userService.retrieveUserById(userId);
        Map<String, String> access = new HashMap<String, String>();
        access.put(userId, "Owner");
        File file = new File(title, fileName, null, new Date(), access);
        fileRepository.save(file);
        List<File> userFiles = user.getFiles();
        userFiles.add(file);
        user.setFiles(userFiles);
        userService.writeUser(user);
    }

    public void updateDocForUser(String userId, String fileName) {
        User user = userService.retrieveUserById(userId);
        File file = fileRepository.findFileByFileName(fileName);
        file.setTime(new Date());
        fileRepository.save(file);
        List<File> userFiles = user.getFiles();
        userFiles.removeIf(userFile -> userFile.getFileName().equals(fileName));
        userFiles.add(file);
        user.setFiles(userFiles);
        userService.writeUser(user);
    }

}
