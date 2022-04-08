package com.springkafka.springkafka.producer;

import com.springkafka.springkafka.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserProducer {
    private static final Logger logger = LoggerFactory.getLogger(UserProducer.class);
    private final String topic;
    private final KafkaTemplate<String, User> kafkaTemplate;

    public UserProducer(@Value("${topic.name}") String topic, KafkaTemplate<String, User> kafkaTemplate){
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(User user){
        kafkaTemplate.send(topic, user).addCallback(
            success -> logger.info("Message send" + success.getProducerRecord().value()),
            failure -> logger.info("Failure send" + failure.getMessage()));
    }

}