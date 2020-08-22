package com.sprint.summerproject.services;

import com.sprint.summerproject.exceptions.UserExistException;
import com.sprint.summerproject.models.Group;
import com.sprint.summerproject.models.Notice;
import com.sprint.summerproject.models.TeamNotice;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final NoticeService noticeService;
    private final GroupService groupService;

    public UserService(UserRepository userRepository, NoticeService noticeService, GroupService groupService) {
        this.userRepository = userRepository;
        this.noticeService = noticeService;
        this.groupService = groupService;
    }

    public void createUserByTel(String tel, String password) throws UserExistException {
        User user = userRepository.findUserByTel(tel);
        if (user == null) {
            userRepository.save(new User("手机用户" + tel,
                    "", tel, password));
        }
        else {
            throw new UserExistException();
        }
    }

    public void createUserByEmail(String email, String password) throws UserExistException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            userRepository.save(new User(email.split("@")[0],
                    email, "", password));
        }
        else {
            throw new UserExistException();
        }
    }

    public List<User> searchUser(String userId) {
        String userRegex = "^" + userId + ".*";
        Set<User> usersByTel = userRepository.findUsersByTelRegex(userRegex);
        Set<User> usersByEmail = userRepository.findUsersByEmailRegex(userRegex);
        usersByTel.addAll(usersByEmail);
        return new ArrayList<User>(usersByTel);
    }

    public User retrieveUserByTel(String tel) {
        return userRepository.findUserByTel(tel);
    }

    public User retrieveUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<Notice> retrieveNotice(String userId) {
        List<String> noticeIds = userRepository.findUserById(userId).getNotices();
        List<Notice> notices = new ArrayList<Notice>();
        for (String noticeId : noticeIds) {
            notices.add(noticeService.retrieveNotice(noticeId));
        }
        return notices;
    }

    public List<TeamNotice> retrieveTeamNotice(String userId) {
        List<String> teamNoticeIds = userRepository.findUserById(userId).getTeamNotices();
        List<TeamNotice> teamNotices = new ArrayList<TeamNotice>();
        for (String teamNoticeId : teamNoticeIds) {
            teamNotices.add(noticeService.retrieveTeamNotice(teamNoticeId));
        }
        return teamNotices;
    }

    public int retrieveUnread(String userId) {
        int res = 0;
        for (String teamNotice : userRepository.findUserById(userId).getTeamNotices()) {
            if (noticeService.retrieveIsReadTeamNotice(teamNotice))
                break;
            res++;
        }
        for (String notice : userRepository.findUserById(userId).getNotices()) {
            if (noticeService.retrieveIsReadNotice(notice))
                break;
            res++;
        }
        return res;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public User retrieveUserById(String id) {
        return userRepository.findUserById(id);
    }

    public void readNotice(String userId, String noticeId) {
        boolean can = false;
        for (String notice : userRepository.findUserById(userId).getNotices()) {
            if (notice.equals(noticeId))
                can = true;
            if (noticeService.retrieveNotice(noticeId).getRead())
                break;
            if (can)
                noticeService.retrieveNotice(noticeId).setRead(true);
        }
    }

    public void readTeamNotice(String userId, String noticeId) {
        boolean can = false;
        for (String notice : userRepository.findUserById(userId).getTeamNotices()) {
            if (notice.equals(noticeId))
                can = true;
            if (noticeService.retrieveTeamNotice(noticeId).getRead())
                break;
            if (can)
                noticeService.retrieveTeamNotice(noticeId).setRead(true);
        }
    }

    public void addTeamNotice(String userId, String noticeId) {
        User user = userRepository.findUserById(userId);
        LinkedList<String> teamNotice = (LinkedList<String>) user.getTeamNotices();
        teamNotice.addFirst(noticeId);
        userRepository.save(user);
    }

    public void addNotice(String userId, String noticeId) {
        User user = userRepository.findUserById(userId);
        LinkedList<String> notice = (LinkedList<String>) user.getNotices();
        notice.addFirst(noticeId);
        userRepository.save(user);
    }

    public User writeUser(User user) {
        return userRepository.save(user);
    }

    public List<Group> getGroupsUserBelongsTo(String userId) {
        User user = userRepository.findUserById(userId);
        List<String> userGroupsIdList = user.getGroups();
        List<Group> userGroups = new ArrayList<>();
        for (String userGroupId : userGroupsIdList) {
            userGroups.add(groupService.retrieveGroupById(userGroupId));
        }
        return userGroups;
    }

}
