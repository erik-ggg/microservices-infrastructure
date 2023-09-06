package com.training.order.application.controller;

import com.training.order.application.service.OrderService;
import com.training.order.domain.model.Order;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
@AllArgsConstructor
public class OrderController {

    private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @PostMapping
    public Order addOrder(@RequestParam UUID userId, @RequestParam UUID itemId, @RequestParam int quantity) {
        return this.orderService.addNewOrder(userId, itemId, quantity);
    }

    @GetMapping
    public List<Order> getOrdersByUserId(@RequestParam UUID userId, @RequestParam UUID transactionId) {
        logger.info("Received request: GET /getOrdersByUserId with TID: {}", transactionId);
        var orders = this.orderService.getOrdersByUserId(userId);
        logger.info("GET {} /getOrdersByUserId returned {}", transactionId, orders);
        return orders;
    }
}
