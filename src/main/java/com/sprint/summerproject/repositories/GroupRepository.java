package com.sprint.summerproject.repositories;

import com.sprint.summerproject.models.Group;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends MongoRepository<Group, String> {
    public Group findGroupById(String id);
    public Group findGroupByName(String name);
}
