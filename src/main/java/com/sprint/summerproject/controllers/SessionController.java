package com.sprint.summerproject.controllers;

import com.sprint.summerproject.models.User;
import com.sprint.summerproject.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    private final UserService userService;

    public SessionController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/session/tel")
    public String verifyTel(@RequestParam String tel, @RequestParam String password) {
        User user = userService.retrieveUserByTel(tel);
        if (user != null) {
            return password.equals(user.getPassword()) ? "Yes" : "No";
        }
        else {
            return "No";
        }
    }

    @PostMapping("/session/email")
    public String verifyEmail(@RequestParam String email, @RequestParam String password) {
        User user = userService.retrieveUserByEmail(email);
        if (user != null) {
            return password.equals(user.getPassword()) ? "Yes" : "No";
        }
        else {
            return "No";
        }
    }

}
