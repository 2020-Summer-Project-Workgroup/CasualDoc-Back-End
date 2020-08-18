package com.sprint.summerproject.utils;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class CodeGenerator {

    public String generateCode() {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }

}
