package com.training.order.application.usecases;

import com.training.order.domain.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderUseCase {

    Order addNewOrder(UUID userId, UUID itemId, int quantity);

    List<Order> getOrdersByUserId(UUID userId);
}
