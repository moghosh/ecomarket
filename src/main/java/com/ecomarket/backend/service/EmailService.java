package com.ecomarket.backend.service;

import com.ecomarket.web.domain.frontend.FeedbackPojo;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by moghosh on 1/9/18.
 */
public interface EmailService {

    /**
     * Sends an email with the content in the Feedback pojo
     * @param feedbackPojo the Feedback pojo
     */
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo);


    /**
     * Sends an email with the content of the Simple Mail Message object
     * @param mailMessage the Simple mail message object containing the email content
     */
    public void sendGenericEmailMessage(SimpleMailMessage mailMessage);

}
