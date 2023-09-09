package com.training.order.infrastructure.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Configuration
public class WarehouseRestTemplate {

    private final static String WAREHOUSE_API_URL = "http://localhost:8081/api/item/";

    public Long callGetItemById(Long itemId, UUID transactionId) {
        var url = WAREHOUSE_API_URL + itemId + "?transactionId" + transactionId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, Long.class).getBody();
    }
}
