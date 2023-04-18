package com.interpackage.notifications.config;

import com.interpackage.basedomains.dto.UserEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UserConsumer {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(UserConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(UserEvent event){
        LOGGER.info(String.format("UserEvent received => %s", event.toString()));

    }

}
