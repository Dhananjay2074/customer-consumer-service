package com.prokarma.assignment.customer.consumer.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prokarma.assignment.consumer.domain.AuditLog;
import com.prokarma.assignment.consumer.kafka.domain.KafkaCustomerRequest;
import com.prokarma.assignment.consumer.repository.AuditLogRepository;
import com.prokarma.assignment.consumer.service.DefaultCustomerService;

@ExtendWith(MockitoExtension.class)
class DefaultCustomerServiceTest {

    @InjectMocks
    private DefaultCustomerService defaultCustomerService;

    @Mock
    private AuditLogRepository auditLogRepository;

    @Test
    void testInvokeCustomerService() throws JsonProcessingException {
        DefaultCustomerService mockDefaultCustomerService = mock(DefaultCustomerService.class);
        when(auditLogRepository.save(Mockito.any(AuditLog.class))).thenReturn(getAuditLog());
        defaultCustomerService.invokeCustomerService(getCustomerData());
        doAnswer((i) -> {
            KafkaCustomerRequest customerRequest = i.getArgument(0);
            assertEquals("EE89878765", customerRequest.getCustomerNumber());
            return null;
        }).when(mockDefaultCustomerService).invokeCustomerService(getCustomerData());
        mockDefaultCustomerService.invokeCustomerService(getCustomerData());
        verify(mockDefaultCustomerService, times(1)).invokeCustomerService(getCustomerData());

    }

    private KafkaCustomerRequest getCustomerData() {

        KafkaCustomerRequest customerRequest = new KafkaCustomerRequest();
        customerRequest.setCustomerNumber("EE89878765");
        customerRequest.setFirstName("Phillps");
        customerRequest.setLastName("Alexander");
        customerRequest.setBirthdate("01-12-1980");
        customerRequest.setCountry("India");
        customerRequest.setCountryCode("IN");
        customerRequest.setMobileNumber("9898767654");
        customerRequest.setEmail("phillips@gmail.com");
        customerRequest.setCustomerStatus("Open");
        customerRequest.setAddress("address");

        return customerRequest;
    }

    private AuditLog getAuditLog() {
        AuditLog auditLog = new AuditLog();
        auditLog.setCustomerNumber("EE89878765");
        auditLog.setPayload(getCustomerData());
        return auditLog;
    }

}
