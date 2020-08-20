package com.sprint.summerproject.controllers;

import com.sprint.summerproject.exception.UserExistException;
import com.sprint.summerproject.models.Notice;
import com.sprint.summerproject.models.TeamNotice;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.UserRepository;
import com.sprint.summerproject.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user/all")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user/tel")
    public User getUserByTel(@RequestParam String tel) {
        return userService.retrieveUserByTel(tel);
    }

    @GetMapping("/user/email")
    public User getUserByEmail(@RequestParam String email) {
        return userService.retrieveUserByEmail(email);
    }

    @GetMapping("/user/regex")
    public List<User> searchUser(@RequestParam String userId) {
        return userRepository.findUsersByTelRegex(userId);
    }

    @GetMapping("/user/notice/number")
    public int getNoticeNum(@RequestParam String userId) {
        return userService.retrieveUnread(userId);
    }

    @GetMapping("/user/notice")
    public List<Notice> getNotice(@RequestParam String userId) {
        return userService.retrieveNotice(userId);
    }

    @GetMapping("/user/teamNotice")
    public List<TeamNotice> getTeamNotice(@RequestParam String userId) {
        return userService.retrieveTeamNotice(userId);
    }

    @PostMapping("/user/tel")
    public String addUserByTel(@RequestParam String tel, @RequestParam String password) {
        try {
            userService.createUserByTel(tel, password);
            return "Yes";
        } catch (UserExistException e) {
            return "No";
        }
    }

    @PostMapping("/user/email")
    public String addUserByEmail(@RequestParam String email, @RequestParam String password) {
        try {
            userService.createUserByEmail(email, password);
            return "Yes";
        } catch (UserExistException e) {
            return "No";
        }
    }

    @PutMapping("/user/notice/read")
    public String readNotice(@RequestParam String userId, @RequestParam String noticeId) {
        try {
            userService.readNotice(userId, noticeId);
            return "Yes";
        } catch (Throwable e) {
            return "No";
        }
    }

    @PutMapping("/user/teamNotice/read")
    public String readTeamNotice(@RequestParam String userId, @RequestParam String noticeId) {
        try {
            userService.readTeamNotice(userId, noticeId);
            return "Yes";
        } catch (Throwable e) {
            return "No";
        }
    }

    @GetMapping("/user/info")
    public User getUserInfo(@RequestParam String id) {
        return userService.retrieveUserById(id);
    }

    @PutMapping("/user/info")
    public String updateUserInfo(@RequestParam String id,
                                 @RequestParam String field,
                                 @RequestParam int type) {
        User user = userService.retrieveUserById(id);
        switch (type) {
            case 0:
                user.setName(field);
                userService.writeUser(user);
                break;
            case 1:
                User someUser1 = userService.retrieveUserByEmail(field);
                if (someUser1 == null || someUser1.getId().equals(user.getId())) {
                    user.setEmail(field);
                    userService.writeUser(user);
                }
                else {
                    return "No";
                }
                break;
            case 2:
                User someUser2 = userService.retrieveUserByTel(field);
                if (someUser2 == null || someUser2.getId().equals(user.getId())) {
                    user.setTel(field);
                    userService.writeUser(user);
                }
                else {
                    return "No";
                }
                break;
        }
        return "Yes";
    }

    @PutMapping("user/password")
    public String updateUserPassword(@RequestParam String id,
                                     @RequestParam String oldPassword,
                                     @RequestParam String newPassword) {
        User user = userService.retrieveUserById(id);
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userService.writeUser(user);
            return "Yes";
        }
        else {
            return "No";
        }
    }

}
