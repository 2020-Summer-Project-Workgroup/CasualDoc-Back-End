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
            userRepository.save(new User("Kawasaki", "kawasaki@gmail.com", "12887364020", "2049"));
            userRepository.save(new User("Richard", "richard@163.com", "50062998023", "1167"));
        };
    }
}
