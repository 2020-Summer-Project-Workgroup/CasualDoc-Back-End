package com.sprint.summerproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Document
public class User {
    @Id
    String id;
    String name;
    String email;
    String tel;
    String password;
    List<String> files;
    List<String> favorites;
    List<String> recentlyViewed;
    List<String> notices;
    List<String> teamNotices;
    List<String> groups;

    public User(String name, String email, String tel, String password) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.files = new ArrayList<>();
        this.favorites = new ArrayList<>();
        this.recentlyViewed = new ArrayList<>();
        this.notices = new LinkedList<String>();
        this.teamNotices = new LinkedList<String>();
        this.groups = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    public List<String> getRecentlyViewed() {
        return recentlyViewed;
    }

    public void setRecentlyViewed(List<String> recentlyViewed) {
        this.recentlyViewed = recentlyViewed;
    }

    public List<String> getNotices() {
        return notices;
    }

    public void setNotices(List<String> notices) {
        this.notices = notices;
    }

    public List<String> getTeamNotices() {
        return teamNotices;
    }

    public void setTeamNotices(List<String> teamNotices) {
        this.teamNotices = teamNotices;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

}
