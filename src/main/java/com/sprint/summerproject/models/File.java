package com.sprint.summerproject.models;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Document
public class File {
    @Id
    String id;
    String fileName;
    String title;
    Binary content;
    Date time;
    List<Comment> comments;
    Map<String, String> access;
    boolean favorite;

    public File(String title, String fileName, Binary content, Date time, Map<String, String> access) {
        this.fileName = fileName;
        this.title = title;
        this.content = content;
        this.time = time;
        this.comments = new ArrayList<>();
        this.access = access;
        this.favorite = false;
    }

    public String getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Binary getContent() {
        return content;
    }

    public void setContent(Binary content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Map<String, String> getAccess() {
        return access;
    }

    public void setAccess(Map<String, String> access) {
        this.access = access;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }
}
