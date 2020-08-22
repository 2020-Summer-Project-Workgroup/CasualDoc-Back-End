package com.sprint.summerproject.controllers;

import com.sprint.summerproject.services.NoticeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @PutMapping("/notice/team")
    public void updateTeamNoticeStatus(String noticeId, int type) {
        noticeService.updateTeamNoticeStatus(noticeId, type);
    }

    @PostMapping("user/notice/team")
    public String inviteUser(@RequestParam String userId, @RequestParam String groupId) {
        return "Yes";
    }

}
