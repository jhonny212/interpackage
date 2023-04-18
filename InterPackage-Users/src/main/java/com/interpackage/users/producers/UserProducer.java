package com.interpackage.users.producers;

import com.interpackage.basedomains.dto.UserEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class UserProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProducer.class);
    private NewTopic topic;
    private KafkaTemplate<String, UserEvent> kafkaTemplate;

    public UserProducer(NewTopic topic, KafkaTemplate<String, UserEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    } 
    
    public void SendMessage(UserEvent event){
        LOGGER.info(String.format("UserEvent => %s", event.toString()));
        //Message
        Message<UserEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();
        kafkaTemplate.send(message);
    }
}
