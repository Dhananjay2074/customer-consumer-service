package com.prokarma.assignment.consumer.service;

import com.prokarma.assignment.consumer.kafka.domain.KafkaCustomerRequest;

public interface CustomerService {

    public void invokeCustomerService(KafkaCustomerRequest kafkaCustomerRequest);
}
