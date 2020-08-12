package com.sprint.summerproject.services;


import com.sprint.summerproject.repository.LoginRepository;
import com.sprint.summerproject.models.User;

public class SessionService {

    private LoginRepository users;

    public User dologin(User users) {
        users=this.users.login(users.getName(), users.getPassword());
        return users;
    }
}