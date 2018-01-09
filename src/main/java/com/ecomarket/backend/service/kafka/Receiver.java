package com.ecomarket.backend.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by moghosh on 1/8/18.
 */
@Service
public class Receiver {

    /** The application logger */
    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${kafka.topic.siteconfig}")
    public void receive(ConsumerRecord<?,?> consumerRecord){
        LOG.info("received payload='{}'", consumerRecord.toString());
        latch.countDown();
    }

}
