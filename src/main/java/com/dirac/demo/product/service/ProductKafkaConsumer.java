package com.dirac.demo.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProductKafkaConsumer {

    private final Logger log = LoggerFactory.getLogger(ProductKafkaConsumer.class);
    private static final String TOPIC = "topic_product";

    @KafkaListener(topics = "topic_product", groupId = "group_id")
    public void consume(String message) throws IOException {
        log.info("Consumed message in {} : {}", TOPIC, message);
    }
}
