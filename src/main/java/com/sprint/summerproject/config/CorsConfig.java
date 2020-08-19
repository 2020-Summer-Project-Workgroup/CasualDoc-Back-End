package com.sprint.summerproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    static final String ORIGINS[] = new String[] { "GET", "POST", "PUT", "DELETE" };
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://39.97.237.71")
                .allowCredentials(true)
                .allowedMethods(ORIGINS)
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
