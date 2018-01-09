package com.ecomarket.web.controllers;

import com.ecomarket.backend.service.EmailService;
import com.ecomarket.web.domain.frontend.FeedbackPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by moghosh on 1/9/18.
 */

@Controller
public class ContactController {
    
    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    //The key which identified the feedback payload in the Model
    private static final String FEEDBACK_MODEL_KEY = "feedback";

    //The Contact Us view name
    private static final String CONTACT_US_VIEW_NAME = "contact/contact";

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactGET(ModelMap model){
        FeedbackPojo feedbackPojo = new FeedbackPojo();
        model.addAttribute(ContactController.FEEDBACK_MODEL_KEY, feedbackPojo);
        return ContactController.CONTACT_US_VIEW_NAME;
    }

    @RequestMapping(value = "/contact", method = RequestMethod.POST)
    public String contactPOST(@ModelAttribute(FEEDBACK_MODEL_KEY) FeedbackPojo feedbackpojo){

        LOG.debug("Feedback Pojo content: {}", feedbackpojo);
        emailService.sendFeedbackEmail(feedbackpojo);
        return ContactController.CONTACT_US_VIEW_NAME;
    }

}
