package com.sprint.summerproject.services;

import com.sprint.summerproject.models.File;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class FileService {

    private final FileRepository fileRepository;
    private final UserService userService;
    @Value("${files.path}")
    private String filePath;

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

    private String readFileToString(String filepath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String s = "";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
        while ((s = bufferedReader.readLine()) != null) {
            stringBuilder.append(s).append("\n");
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }

    public String getDocOfUser(String fileName) throws IOException {
        String filePath = this.filePath + fileName;
        return readFileToString(filePath);
    }

    public List<File> sortFilesByDate(List<File> files) {
        files.sort(Comparator.comparing(File::getTime).reversed());
        return files;
    }

    public List<File> getUserFiles(String id) {
        User user = userService.retrieveUserById(id);
        List<com.sprint.summerproject.models.File> userFiles = user.getFiles();
        return sortFilesByDate(userFiles);
    }

    public String updateUserFavoriteFiles(String id, String fileName) {
        User user = userService.retrieveUserById(id);
        List<File> userFiles = user.getFiles();
        for (File file : userFiles) {
            if (file.getFileName().equals(fileName)) {
                file.setFavorite(true);
                fileRepository.save(file);
            }
        }
        userService.writeUser(user);
        return "Yes";
    }

    public List<File> getUserFavoriteFiles(String id) {
        User user = userService.retrieveUserById(id);
        List<com.sprint.summerproject.models.File> userFiles = user.getFiles();
        List<com.sprint.summerproject.models.File> userFavoriteFiles = new ArrayList<>();
        for (com.sprint.summerproject.models.File file : userFiles) {
            if (file.isFavorite())
                userFavoriteFiles.add(file);
        }
        return userFavoriteFiles;
    }

}
