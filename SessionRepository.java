package com.sprint.summerproject.repositories.;
import com.sprint.summerproject.models.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
public interface SessionRepository {
    public User login(String username, String password);
}