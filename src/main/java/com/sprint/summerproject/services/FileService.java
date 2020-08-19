package com.sprint.summerproject.services;

import com.sprint.summerproject.models.File;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.FileRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void addDocToUser(String userId,
                             String title,
                             String content) {
        User user = userService.retrieveUserById(userId);
        Map<String, String> access = new HashMap<>();
        access.put(userId, "Owner");
        File file = new File(title, content, new Date(), access);
        fileRepository.save(file);
        List<File> userFiles = user.getFiles();
        userFiles.add(file);
        user.setFiles(userFiles);
        userService.writeUser(user);
    }

    public void updateDocForUser(String userId,
                                 String fileId,
                                 String title,
                                 String content) {
        User user = userService.retrieveUserById(userId);
        File file = fileRepository.findFileById(fileId);
        file.setTitle(title);
        file.setContent(content);
        file.setTime(new Date());
        fileRepository.save(file);
        List<File> userFiles = user.getFiles();
        userFiles.removeIf(userFile -> userFile.getId().equals(fileId));
        userFiles.add(file);
        user.setFiles(userFiles);
        userService.writeUser(user);
    }

    public File getDocOfUser(String fileId){
        return fileRepository.findFileById(fileId);
    }

    public void deleteDocFromUser(String userId, String fileId) {
        User user = userService.retrieveUserById(userId);
        List<File> userFiles = user.getFiles();
        userFiles.removeIf(userFile -> userFile.getId().equals(fileId));
        userService.writeUser(user);
    }

    public List<File> sortFilesByDate(List<File> files) {
        files.sort(Comparator.comparing(File::getTime).reversed());
        return files;
    }

    public List<File> getUserFiles(String id) {
        User user = userService.retrieveUserById(id);
        List<File> userFiles = user.getFiles();
        return sortFilesByDate(userFiles);
    }

    public String updateUserFavoriteFiles(String userId, String fileId) {
        User user = userService.retrieveUserById(userId);
        List<File> userFiles = user.getFiles();
        for (File file : userFiles) {
            if (file.getId().equals(fileId)) {
                file.setFavorite(!file.isFavorite());
                fileRepository.save(file);
            }
        }
        userService.writeUser(user);
        return "Yes";
    }

    public List<File> getUserFavoriteFiles(String id) {
        User user = userService.retrieveUserById(id);
        List<File> userFiles = user.getFiles();
        List<File> userFavoriteFiles = new ArrayList<>();
        for (File file : userFiles) {
            if (file.isFavorite())
                userFavoriteFiles.add(file);
        }
        return userFavoriteFiles;
    }

}
