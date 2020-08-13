package com.sprint.summerproject.config;

import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {

        return strings -> {
            userRepository.save(new User("Jerry", "jerryloi@163.com", "13880265015", "2049", null));
        };
    }
}
