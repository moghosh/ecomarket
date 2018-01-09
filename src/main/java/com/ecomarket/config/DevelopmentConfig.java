package com.ecomarket.config;

import com.ecomarket.backend.service.EmailService;
import com.ecomarket.backend.service.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by moghosh on 1/9/18.
 */
@Configuration
@Profile("dev")
@PropertySource("file:///${user.home}/dev/.ecomarket/application-dev.properties")
public class DevelopmentConfig {

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}
