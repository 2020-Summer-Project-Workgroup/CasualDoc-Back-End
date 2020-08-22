package com.sprint.summerproject.services;

import com.sprint.summerproject.models.File;
import com.sprint.summerproject.models.Group;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.*;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;

    public GroupService(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public String createGroup(String userId, String groupName) {
        Map<String, Integer> files = new HashMap<String, Integer>();
        Map<String, List<String>> viewMembers = new HashMap<String, List<String>>();
        Map<String, List<String>> editMembers = new HashMap<String, List<String>>();
        Map<String, Integer> members = new HashMap<String, Integer>();
        members.put(userId, 1);
        Group group = groupRepository.save(new Group(groupName, files, viewMembers, editMembers, members));
        return group.getId();
    }

    public void addGroupMember(String groupId, String memberId) {
        Group group = groupRepository.findGroupById(groupId);
        group.getMembers().put(memberId, 0);
        for (String fileId : group.getFiles().keySet()) {
            group.getViewMembers().get(fileId).add(memberId);
            group.getEditMembers().get(fileId).add(memberId);
        }
        groupRepository.save(group);
        User user = userService.retrieveUserById(memberId);
        List<String> userGroupsIdList = user.getGroups();
        userGroupsIdList.add(groupId);
        userService.writeUser(user);
    }

    public String addFile(String groupId, String ownerId) {
        Group group = groupRepository.findGroupById(groupId);
        Set<String> memberSet = group.getViewMembers().keySet();
        List<String> memberList = new ArrayList<String>(memberSet);
        // 创建文件并获得Id
        String fileId = "<fileId>";
        group.getFiles().put(fileId, 0);
        group.getViewMembers().put(fileId, memberList);
        group.getEditMembers().put(fileId, memberList);
        groupRepository.save(group);
        return fileId;
    }

    public void updateFileStatus(String groupId, String fileId, int status) {
        Group group = groupRepository.findGroupById(groupId);
        group.getFiles().remove(fileId);
        group.getFiles().put(fileId, status);
        groupRepository.save(group);
    }

    public void updateViewMembers(String groupId, String fileId, String[] members) {
        Group group = groupRepository.findGroupById(groupId);
        List<String> memberList = new ArrayList<String>(Arrays.asList(members));
        group.getViewMembers().remove(fileId);
        group.getViewMembers().put(fileId, memberList);
        groupRepository.save(group);
    }

    public void updateEditMembers(String groupId, String fileId, String[] members) {
        Group group = groupRepository.findGroupById(groupId);
        List<String> memberList = new ArrayList<String>(Arrays.asList(members));
        group.getEditMembers().remove(fileId);
        group.getEditMembers().put(fileId, memberList);
        groupRepository.save(group);
    }

    public Map<String, Integer> retrieveFiles(String groupId) {
        return groupRepository.findGroupById(groupId).getFiles();
    }

    public Map<String, Integer> retrieveMembers(String groupId) {
        return groupRepository.findGroupById(groupId).getMembers();
    }

    public List<String> retrieveFileViewMembers(String groupId, String fileId) {
        return groupRepository.findGroupById(groupId).getViewMembers().get(fileId);
    }

    public List<String> retrieveFileEditMembers(String groupId, String fileId) {
        return groupRepository.findGroupById(groupId).getEditMembers().get(fileId);
    }

    public Group retrieveGroupById(String id) {
        return groupRepository.findGroupById(id);
    }

}
