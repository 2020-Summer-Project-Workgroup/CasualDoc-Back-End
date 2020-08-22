package com.sprint.summerproject.controllers;

import com.sprint.summerproject.models.Group;
import com.sprint.summerproject.services.GroupService;
import com.sprint.summerproject.utils.FileRightsResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/group")
    public Group addGroup(@RequestParam String userId, @RequestParam String groupName) {
        return groupService.createGroup(userId, groupName);
    }

    @PutMapping("/group/members")
    public String addMember(@RequestParam String groupId, @RequestParam String memberId) {
        try {
            groupService.addGroupMember(groupId, memberId);
            return "Yes";
        } catch (Exception e) {
            return "No";
        }
    }

    @PostMapping("/group/files/plain")
    public String addFile(@RequestParam String groupId, @RequestParam String ownerId) {
        try {
            return groupService.addFile(groupId, ownerId);
        } catch (Exception e) {
            return "No";
        }
    }

    @PutMapping("/group/files/outer")
    public String makeFilePublic(@RequestParam String groupId, @RequestParam String fileId) {
        try {
            groupService.updateFileStatus(groupId, fileId, 1);
            return "Yes";
        } catch (Exception e) {
            return "No";
        }
    }

    @PutMapping("/group/files/inner")
    public String updateFileRights(@RequestParam String groupId,
                                   @RequestParam String fileId,
                                   @RequestParam String[] viewMembers,
                                   @RequestParam String[] editMembers) {
        try {
            groupService.updateFileStatus(groupId, fileId, 0);
            groupService.updateViewMembers(groupId, fileId, viewMembers);
            groupService.updateEditMembers(groupId, fileId, editMembers);
            return "Yes";
        } catch (Exception e) {
            return "No";
        }
    }

    @GetMapping("/group/files")
    public Map<String, Integer> getFiles(@RequestParam String groupId) {
        return groupService.retrieveFiles(groupId);
    }

    @GetMapping("/group/members")
    public Map<String, Integer> getMembers(@RequestParam String groupId) {
        return groupService.retrieveMembers(groupId);
    }

    @GetMapping("/group/rights")
    public FileRightsResponse getFileRights(@RequestParam String groupId, @RequestParam String fileId) {
        List<String> viewMembers = groupService.retrieveFileViewMembers(groupId, fileId);
        List<String> editMembers = groupService.retrieveFileEditMembers(groupId, fileId);
        return new FileRightsResponse(viewMembers, editMembers);
    }
}
