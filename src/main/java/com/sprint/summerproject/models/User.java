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
    List<String> notices;
    List<String> teamNotices;

    public User(String name, String email, String tel, String password) {
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.password = password;
        this.files = new ArrayList<String>();
        this.notices = new LinkedList<String>();
        this.teamNotices = new LinkedList<String>();
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
}
