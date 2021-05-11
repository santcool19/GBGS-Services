package com.gbgs.edu.kafka.consumer;

import com.gbgs.edu.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.List;

public class KafkaConsumerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(id = "batch-listener", topics = "${kafka.topic.name}")
    public void receive(List<Customer> data, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) List<Integer> partitions, @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        LOGGER.info("start of batch receive");
        for (int i = 0; i < data.size(); i++) {
            LOGGER.info("received message='{}' with partition-offset='{}'", data.get(i).getName(), partitions.get(i) + "-" + offsets.get(i));
        }
        LOGGER.info("end of batch receive");
    }
}
