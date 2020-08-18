package com.sprint.summerproject.controllers;

import com.sprint.summerproject.models.Group;
import com.sprint.summerproject.services.GroupService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/group")
    public String addGroup(@RequestParam String userId, @RequestParam String groupName) {
        return groupService.createGroup(userId, groupName);
    }

    @PutMapping("/group/members")
    public String addMember(@RequestParam String groupId, @RequestParam String memberId) {

    }
}
