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
                                 @RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String tel) {
        User user = userService.retrieveUserById(id);
        int modifiedFieldsWeight = 0;
        if (!email.equals(user.getEmail())) {
            modifiedFieldsWeight += 1;
        }
        if (!tel.equals(user.getTel())) {
            modifiedFieldsWeight += 2;
        }
        user.setName(name);
        switch (modifiedFieldsWeight) {
            case 0:
                userService.writeUser(user);
                break;
            case 1:
                if (userService.retrieveUserByEmail(email) == null) {
                    user.setEmail(email);
                    userService.writeUser(user);
                }
                else {
                    return "Email already registered";
                }
                break;
            case 2:
                if (userService.retrieveUserByTel(tel) == null) {
                    user.setTel(tel);
                    userService.writeUser(user);
                }
                else {
                    return "Tel already registered";
                }
                break;
            case 3:
                if (userService.retrieveUserByEmail(email) == null && userService.retrieveUserByTel(tel) == null) {
                    user.setEmail(email);
                    user.setTel(tel);
                    userService.writeUser(user);
                }
                else {
                    return "Email or tel already registered";
                }
                break;
        }
        return "User information updated successfully";
    }

    @PutMapping("user/password")
    public String updateUserPassword(@RequestParam String id,
                                     @RequestParam String oldPassword,
                                     @RequestParam String newPassword) {
        User user = userService.retrieveUserById(id);
        if (oldPassword.equals(user.getPassword())) {
            user.setPassword(newPassword);
            userService.writeUser(user);
            return "Password updated successfully";
        }
        else {
            return "Wrong old password";
        }
    }

}
