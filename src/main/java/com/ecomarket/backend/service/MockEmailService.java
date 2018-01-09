package com.ecomarket.backend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by moghosh on 1/9/18.
 */
public class MockEmailService extends AbstractEmailService {
    
    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);

    @Override
    public void sendGenericEmailMessage(SimpleMailMessage mailMessage) {
        LOG.debug("Simulating an Email Service");
        LOG.info(mailMessage.toString());
        LOG.debug("Email sent");
    }
}
