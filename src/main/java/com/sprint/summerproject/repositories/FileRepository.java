package com.sprint.summerproject.repositories;

import com.sprint.summerproject.models.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends MongoRepository<File, String> {
    public File findFileByFileName(String id);
}
