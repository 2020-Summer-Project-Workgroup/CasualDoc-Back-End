package com.sprint.summerproject.services;

import com.sprint.summerproject.models.Notice;
import com.sprint.summerproject.models.TeamNotice;
import com.sprint.summerproject.repositories.NoticeRepository;
import com.sprint.summerproject.repositories.TeamNoticeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;
    private final TeamNoticeRepository teamNoticeRepository;

    public NoticeService(NoticeRepository noticeRepository,
                         TeamNoticeRepository teamNoticeRepository) {
        this.noticeRepository = noticeRepository;
        this.teamNoticeRepository = teamNoticeRepository;
    }

    public String createCommentNotice(String senderId, String senderName,
                               String content) {
        return noticeRepository
                .save(new Notice(senderId, senderName, content, new Date()))
                .getId();
    }

    public String createInviteNotice(String senderId, String senderName,
                                     String groupName) {
        return teamNoticeRepository
                .save(new TeamNotice(1, senderId, senderName, groupName, new Date()))
                .getId();
    }

    public String createKickNotice(String senderId, String senderName,
                                     String groupName, Date time) {
        return teamNoticeRepository
                .save(new TeamNotice(4, senderId, senderName, groupName, time))
                .getId();
    }

    public void updateTeamNoticeStatus(String noticeId, int type) {
        TeamNotice teamNotice = teamNoticeRepository.findTeamNoticeById(noticeId);
        teamNotice.setType(type);
        teamNoticeRepository.save(teamNotice);
    }

    public void readNotice(String noticeId) {
        Notice notice = noticeRepository.findNoticeById(noticeId);
        notice.setRead(true);
        noticeRepository.save(notice);
    }

    public void readTeamNotice(String noticeId) {
        TeamNotice teamNotice = teamNoticeRepository.findTeamNoticeById(noticeId);
        teamNotice.setRead(true);
        teamNoticeRepository.save(teamNotice);
    }

    public TeamNotice retrieveTeamNotice(String noticeId) {
        return teamNoticeRepository.findTeamNoticeById(noticeId);
    }

    public Notice retrieveNotice(String noticeId) {
        return noticeRepository.findNoticeById(noticeId);
    }

    public boolean retrieveIsReadNotice(String noticeId) {
        return noticeRepository.findNoticeById(noticeId).getRead();
    }

    public boolean retrieveIsReadTeamNotice(String noticeId) {
        return teamNoticeRepository.findTeamNoticeById(noticeId).getRead();
    }
}
