package com.sprint.summerproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Document
public class Group {
    @Id
    String id;
    String name;
    Map<File, Integer> files;
    Map<File, Set<String>> viewMembers;
    Map<File, Set<String>> editMembers;
    Map<String, Integer> members;

    public Group(String name, Map<File, Integer> files, Map<File, Set<String>> viewMembers,
                 Map<File, Set<String>> editMembers, Map<String, Integer> members) {
        this.name = name;
        this.files = files;
        this.viewMembers = viewMembers;
        this.editMembers = editMembers;
        this.members = members;
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

    public Map<File, Integer> getFiles() {
        return files;
    }

    public void setFiles(Map<File, Integer> files) {
        this.files = files;
    }

    public Map<File, Set<String>> getViewMembers() {
        return viewMembers;
    }

    public void setViewMembers(Map<File, Set<String>> viewMembers) {
        this.viewMembers = viewMembers;
    }

    public Map<File, Set<String>> getEditMembers() {
        return editMembers;
    }

    public void setEditMembers(Map<File, Set<String>> editMembers) {
        this.editMembers = editMembers;
    }

    public Map<String, Integer> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Integer> members) {
        this.members = members;
    }
}
