package com.sprint.summerproject.services;

import com.sprint.summerproject.exception.UserExistException;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.UserRepository;
import com.sprint.summerproject.utils.Response;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUserByTel(String tel, String password) throws UserExistException {
        User user = userRepository.findUserByTel(tel);
        if(user == null) {
            userRepository.save(new User("手机用户" + tel,
                    null, tel, password, null));
        } else {
            throw new UserExistException();
        }
    }

    public void createUserByEmail(String email, String password) throws UserExistException {
        User user = userRepository.findUserByEmail(email);
        if(user == null) {
            userRepository.save(new User(email.split("@")[0],
                    email, null, password, null));
        } else {
            throw new UserExistException();
        }
    }

    public User retrieveUserByTel(String tel) {
        return userRepository.findUserByTel(tel);
    }

    public User retrieveUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
