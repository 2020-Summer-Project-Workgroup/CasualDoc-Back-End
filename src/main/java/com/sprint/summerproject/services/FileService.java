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

    public File retrieveFileById(String id) {
        return fileRepository.findFileById(id);
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

    public void deleteDocFromUser(String userId, String fileId) {
        User user = userService.retrieveUserById(userId);
        List<File> userFiles = user.getFiles();
        userFiles.removeIf(userFile -> userFile.getId().equals(fileId));
        user.setFiles(userFiles);
        userService.writeUser(user);
        File file = fileRepository.findFileById(fileId);
        fileRepository.delete(file);
    }

    public List<File> sortFilesByDate(List<File> files) {
        files.sort(Comparator.comparing(File::getTime).reversed());
        return files;
    }

    public List<File> getUserActiveFiles(String userId) {
        User user = userService.retrieveUserById(userId);
        List<File> userFileIdList = user.getFiles();
        List<File> userFiles = new ArrayList<>();
        for (File userFile : userFileIdList) {
            File toBeAdded = fileRepository.findFileById(userFile.getId());
            if (!toBeAdded.isTrashed()) {
                userFiles.add(toBeAdded);
            }
        }
        return sortFilesByDate(userFiles);
    }

    public String updateUserFavoriteFiles(String userId, String fileId) {
        User user = userService.retrieveUserById(userId);
        List<File> userFavorites = user.getFavorites();
        File file = fileRepository.findFileById(fileId);
        boolean fileInList = userFavorites.removeIf(userFavorite -> userFavorite.getId().equals(fileId));
        if (!fileInList) {
            userFavorites.add(file);
        }
        user.setFavorites(userFavorites);
        userService.writeUser(user);
        return "Yes";
    }

    public List<File> getUserFavoriteFiles(String id) {
        User user = userService.retrieveUserById(id);
        return sortFilesByDate(user.getFavorites());
    }

    public File writeFile(File file) {
        return fileRepository.save(file);
    }

    public void updateUserFileStatus(String fileId) {
        File file = fileRepository.findFileById(fileId);
        file.setTrashed(!file.isTrashed());
        fileRepository.save(file);
    }

    public List<File> getUserTrashedFiles(String userId) {
        User user = userService.retrieveUserById(userId);
        List<File> userFileIdList = user.getFiles();
        List<File> userFiles = new ArrayList<>();
        for (File userFile : userFileIdList) {
            File toBeAdded = fileRepository.findFileById(userFile.getId());
            if (toBeAdded.isTrashed()) {
                userFiles.add(toBeAdded);
            }
        }
        return sortFilesByDate(userFiles);
    }

    public void updateUserRecentFiles(String userId, String fileId) {
        User user = userService.retrieveUserById(userId);
        List<String> userRecentlyViewed = user.getRecentlyViewed();
        userRecentlyViewed.add(fileId);
        userService.writeUser(user);
    }

    public List<File> getUserRecentFiles(String userId) {
        User user = userService.retrieveUserById(userId);
        List<String> userRecentlyViewed = user.getRecentlyViewed();
        List<File> userRecentlyViewedFiles = new ArrayList<>();
        for (String item : userRecentlyViewed) {
            userRecentlyViewedFiles.add(fileRepository.findFileById(item));
        }
        return userRecentlyViewedFiles;
    }

}
