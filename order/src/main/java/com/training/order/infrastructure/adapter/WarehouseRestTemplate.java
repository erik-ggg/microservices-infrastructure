package com.training.order.infrastructure.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WarehouseRestTemplate {

    private final static String WAREHOUSE_API_URL = "http://localhost:8081/api/item/";

    public Long callGetItemById(Long itemId) {
        var url = WAREHOUSE_API_URL + itemId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, Long.class).getBody();
    }
}
