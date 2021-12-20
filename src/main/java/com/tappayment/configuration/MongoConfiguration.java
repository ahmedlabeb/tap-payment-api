package com.tappayment.configuration;

import com.tappayment.api.entity.listners.GenerateUUIDListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfiguration {
    @Bean
    public GenerateUUIDListener generateUUIDListener() {
        return new GenerateUUIDListener();
    }

}
