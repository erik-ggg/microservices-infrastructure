package com.training.order.application.service;

import com.training.order.application.usecases.OrderUseCase;
import com.training.order.domain.model.Order;
import com.training.order.infrastructure.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService implements OrderUseCase {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order addNewOrder(UUID userId, UUID itemId, int quantity) {
        var newOrder = new Order(userId, itemId, quantity);
        return orderRepository.save(newOrder);
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        return orderRepository.findAllById(Collections.singletonList(userId));
    }
}
