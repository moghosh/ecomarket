package com.ecomarket.web.controllers;

import com.ecomarket.backend.service.kafka.Sender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by moghosh on 1/9/18.
 */

@RestController
public class KafkaMessageController {
    
    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(KafkaMessageController.class);

    @Autowired
    private Sender sender;

    @Value("${kafka.topic.siteconfig}")
    private String kafkaTopic;

    @RequestMapping(value = "/producer", method = RequestMethod.GET)
    public String producer(@RequestParam("message") String message){

        sender.send(kafkaTopic, message);

        LOG.info("Sending message: {} to Kafka", message);

        return "Message sent to Kafka";

    }


}
