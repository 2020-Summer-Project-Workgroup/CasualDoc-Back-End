package com.sprint.summerproject.config;

import com.sprint.summerproject.models.File;
import com.sprint.summerproject.models.User;
import com.sprint.summerproject.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoDBConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        List<File> files = new ArrayList<>();

        return strings -> {
            userRepository.save(new User("Kawasaki", "kawasaki@gmail.com", "12887364020", "2049", files));
            userRepository.save(new User("Richard", "richard@163.com", "50062998023", "1167", files));
        };
    }
}
