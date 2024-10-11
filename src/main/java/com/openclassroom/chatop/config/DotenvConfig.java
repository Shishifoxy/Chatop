package com.openclassroom.chatop.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        String env = System.getenv("ENV") != null ? System.getenv("ENV") : "dev";
        return Dotenv.configure()
                .filename(".env." + env)
                .load();
    }
}
