package com.sprint.summerproject.repositories;

import com.sprint.summerproject.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    public User findUserById(String id);
    public User findUserByTel(String tel);
    public User findUserByEmail(String email);
    List<User> findUsersByTelRegex(String regex);
}
