package com.training.order.application.controller;

import com.training.order.application.service.OrderService;
import com.training.order.domain.model.Order;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
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
    public Order addOrder(@RequestParam Long userId, @RequestBody Map<Long, Integer> items) {

        log.info("Received order request from {}", userId);
        return this.orderService.addNewOrder(userId, items);
    }

    @GetMapping
    public List<Order> getOrdersByUserId(@RequestParam Long userId) {
        logger.info("Received request: GET /getOrdersByUserId");
        var orders = this.orderService.getOrdersByUserId(userId);
        logger.info("GET /getOrdersByUserId returned {}", orders);
        return orders;
    }

    @PostMapping("/kafka")
    public void postTopic() {
        sendMessage("new message from orders microservice");
    }
}
