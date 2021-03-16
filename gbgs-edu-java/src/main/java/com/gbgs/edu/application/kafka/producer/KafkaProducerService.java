package com.gbgs.edu.application.kafka.producer;

import com.gbgs.edu.application.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerService.class);
    @Value("${kafka.topic.name}")
    private String topic;
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Customer customer) {
        LOGGER.info("sending payload='{}'", customer);
        Message<Customer> message = MessageBuilder
                .withPayload(customer)
                .setHeader(KafkaHeaders.TOPIC, "gbgs")
                .setHeader(KafkaHeaders.PARTITION_ID, 1)
                .build();
        this.kafkaTemplate.send(message);
    }
}
