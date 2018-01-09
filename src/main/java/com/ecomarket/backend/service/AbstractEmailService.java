package com.ecomarket.backend.service;

import com.ecomarket.web.domain.frontend.FeedbackPojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by moghosh on 1/9/18.
 */
public abstract class AbstractEmailService implements EmailService {

    @Value("${default.to.address}")
    private String defaultToAddrress;

    /**
     * Creates a Simple Mail Message from Feedback pojo
     * @param feedbackPojo The Feedback pojo
     * @return
     */
    protected SimpleMailMessage prepareSimpleMailMessageFromFeedbackPojo(FeedbackPojo feedbackPojo){

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(defaultToAddrress);
        simpleMailMessage.setFrom(feedbackPojo.getEmail());
        simpleMailMessage.setSubject("Feedback Received from " + feedbackPojo.getFirstName() + " "
                + feedbackPojo.getLastName());
        simpleMailMessage.setText(feedbackPojo.getFeedback());
        return simpleMailMessage;
    }


    @Override
    public void sendFeedbackEmail(FeedbackPojo feedbackPojo){
        sendGenericEmailMessage(prepareSimpleMailMessageFromFeedbackPojo(feedbackPojo));
    }
}
