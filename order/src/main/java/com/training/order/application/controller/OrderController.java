package com.training.order.application.controller;

import com.training.order.application.service.OrderService;
import com.training.order.domain.model.Order;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        kafkaTemplate.send("MySexyTopic", msg);
    }

    @PostMapping
    public Order addOrder(@RequestParam Long userId, @RequestBody Map<Long, Integer> items, @RequestBody UUID transactionId) {
        return this.orderService.addNewOrder(userId, items, transactionId);
    }

    @GetMapping
    public List<Order> getOrdersByUserId(@RequestParam Long userId, @RequestParam UUID transactionId) {
        logger.info("Received request: GET /getOrdersByUserId with TID: {}", transactionId);
        var orders = this.orderService.getOrdersByUserId(userId);
        logger.info("GET {} /getOrdersByUserId returned {}", transactionId, orders);
        return orders;
    }

    @PostMapping("/kafka")
    public void postTopic() {
        sendMessage("new message from orders microservice");
    }
}
