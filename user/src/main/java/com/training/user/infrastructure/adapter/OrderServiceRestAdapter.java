package com.training.user.infrastructure.adapter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Component
public class OrderServiceRestAdapter {

    private final static String ORDERS_URL = "http://localhost:8082/api/order";


    public void makeUserOrdersRequest(UUID userId, UUID transactionId) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response
                = restTemplate.getForEntity(ORDERS_URL, String.class);
    }
}
