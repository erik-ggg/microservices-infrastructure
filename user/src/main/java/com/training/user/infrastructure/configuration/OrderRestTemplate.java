package com.training.user.infrastructure.configuration;


import com.training.user.application.dto.PurchaseDTO;
import com.training.user.application.dto.order.OrderDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class OrderRestTemplate {

    private final static String ORDER_API_URL = "http://localhost:8082/api/order";

    public OrderDTO callUserOrdersRequest(Long userID) {
        var url = ORDER_API_URL + "?userId=" + userID;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(url, OrderDTO.class).getBody();
    }

    public void createPurchase(PurchaseDTO purchaseDTO) {
        var url = ORDER_API_URL + "?userId=" + purchaseDTO.userId();

        // Create a map with the item ID and quantity
        Map<Long, Integer> items = new HashMap<>();
        items.put(Long.parseLong(purchaseDTO.itemId().toString()), purchaseDTO.quantity());

        // Set up headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity with the items map as the body
        HttpEntity<Map<Long, Integer>> requestEntity = new HttpEntity<>(items, headers);

        // Make the POST request
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, requestEntity, UUID.class);
    }
}
