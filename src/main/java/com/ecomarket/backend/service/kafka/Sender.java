package com.ecomarket.backend.service.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by moghosh on 1/8/18.
 */
@Service
public class Sender {
    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload){
        LOG.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic,payload);
    }

}
