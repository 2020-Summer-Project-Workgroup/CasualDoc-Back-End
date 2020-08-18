package com.sprint.summerproject.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document
public class Group {
    @Id
    String id;
    String name;
    Map<String, Integer> files;
    Map<String, List<String>> viewMembers;
    Map<String, List<String>> editMembers;
    Map<String, Integer> members;

    public Group(String name, Map<String, Integer> files,
                 Map<String, List<String>> viewMembers,
                 Map<String, List<String>> editMembers,
                 Map<String, Integer> members) {
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

    public Map<String, Integer> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Integer> files) {
        this.files = files;
    }

    public Map<String, List<String>> getViewMembers() {
        return viewMembers;
    }

    public void setViewMembers(Map<String, List<String>> viewMembers) {
        this.viewMembers = viewMembers;
    }

    public Map<String, List<String>> getEditMembers() {
        return editMembers;
    }

    public void setEditMembers(Map<String, List<String>> editMembers) {
        this.editMembers = editMembers;
    }

    public Map<String, Integer> getMembers() {
        return members;
    }

    public void setMembers(Map<String, Integer> members) {
        this.members = members;
    }
}
