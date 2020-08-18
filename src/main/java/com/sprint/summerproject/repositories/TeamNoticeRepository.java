package com.sprint.summerproject.repositories;

import com.sprint.summerproject.models.TeamNotice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamNoticeRepository extends MongoRepository<TeamNotice, String> {
}
