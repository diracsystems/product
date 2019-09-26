package com.dirac.demo.product.web.rest;

import com.dirac.demo.product.service.ProductKafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/product-kafka")
public class ProductKafkaResource {

    private final Logger log = LoggerFactory.getLogger(ProductKafkaResource.class);

    private ProductKafkaProducer kafkaProducer;

    public ProductKafkaResource(ProductKafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam("message") String message) {
        log.debug("REST request to send to Kafka topic the message : {}", message);
        this.kafkaProducer.sendMessage(message);
    }
}
