package com.dirac.demo.product.web.rest;

import com.dirac.demo.product.ProductApp;
import com.dirac.demo.product.config.TestSecurityConfiguration;
import com.dirac.demo.product.service.ProductKafkaProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@EmbeddedKafka
@SpringBootTest(classes = {ProductApp.class, TestSecurityConfiguration.class})
public class ProductKafkaResourceIT {

    @Autowired
    private ProductKafkaProducer kafkaProducer;

    private MockMvc restMockMvc;

    @BeforeEach
    public void setup() {
        ProductKafkaResource kafkaResource = new ProductKafkaResource(kafkaProducer);

        this.restMockMvc = MockMvcBuilders.standaloneSetup(kafkaResource)
            .build();
    }

    @Test
    public void sendMessageToKafkaTopic() throws Exception {
        restMockMvc.perform(post("/api/product-kafka/publish?message=yolo"))
            .andExpect(status().isOk());
    }
}
