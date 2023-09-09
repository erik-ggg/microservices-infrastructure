package com.training.order.application.usecases;

import com.training.order.domain.model.Order;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface OrderUseCase {

    Order addNewOrder(Long userId, Map<Long, Integer> items, UUID transactionId);

    List<Order> getOrdersByUserId(Long userId);
}
