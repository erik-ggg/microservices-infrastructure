package com.training.user.infrastructure.configuration;


import com.training.user.application.dto.order.OrderDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Configuration
public class OrderRestTemplate {

    private final static String ORDER_API_URL = "http://localhost:8082/api/order";

    public OrderDTO callUserOrdersRequest(Long userID, UUID transactionId) {
        var url = ORDER_API_URL + "?userId=" + userID + "&transactionId" + transactionId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, OrderDTO.class).getBody();
    }
}
