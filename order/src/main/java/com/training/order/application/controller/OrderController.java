package com.training.order.application.controller;

import com.training.order.application.service.OrderService;
import com.training.order.domain.model.Order;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order addOrder(@RequestParam UUID userId, @RequestParam UUID itemId, @RequestParam int quantity) {
        return this.orderService.addNewOrder(userId, itemId, quantity);
    }

    @GetMapping
    public List<Order> getOrdersByUserId(@RequestParam UUID userId) {
        return this.orderService.getOrdersByUserId(userId);
    }
}
