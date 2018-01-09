package com.ecomarket.config;

import com.ecomarket.backend.service.EmailService;
import com.ecomarket.backend.service.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by moghosh on 1/9/18.
 */

@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/dev/.ecomarket/application-prod.properties")
public class ProductionConfig {

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }
}
