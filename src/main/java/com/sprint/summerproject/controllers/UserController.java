package com.sprint.summerproject.controllers;

import com.sprint.summerproject.exception.UserExistException;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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

    @PutMapping("user/info")
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
