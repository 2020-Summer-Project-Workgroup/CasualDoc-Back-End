package com.sprint.summerproject.services;

import com.sprint.summerproject.models.File;
import com.sprint.summerproject.models.Group;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class GroupService {

    private final GroupRepository groupRepository;
    private final UserService userService;

    public GroupService(GroupRepository groupRepository, UserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public String createGroup(String userId, String groupName) {
        Map<File, Integer> files = new HashMap<File, Integer>();
        Map<File, Set<String>> viewMembers = new HashMap<File, Set<String>>();
        Map<File, Set<String>> editMembers = new HashMap<File, Set<String>>();
        Map<User, Integer> members = new HashMap<User, Integer>();
        User owner = userService.retrieveUserById(userId);
        members.put(owner, 1);
        Group group = groupRepository.save(new Group(groupName, files, viewMembers, editMembers, members));
        return group.getId();
    }



    public Group retrieveGroupByName(String groupName) {
        return groupRepository.findGroupByName(groupName);
    }
}
