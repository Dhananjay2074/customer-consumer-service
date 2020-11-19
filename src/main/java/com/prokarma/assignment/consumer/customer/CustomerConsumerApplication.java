package com.prokarma.assignment.consumer.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class CustomerConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerConsumerApplication.class, args);
    }

}
