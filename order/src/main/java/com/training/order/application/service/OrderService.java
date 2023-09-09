package com.training.order.application.service;

import com.training.order.application.usecases.OrderUseCase;
import com.training.order.domain.model.Order;
import com.training.order.domain.model.OrderItemPK;
import com.training.order.domain.model.OrderItems;
import com.training.order.infrastructure.adapter.WarehouseRestTemplate;
import com.training.order.infrastructure.repository.OrderItemsRepository;
import com.training.order.infrastructure.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
public class OrderService implements OrderUseCase {

    private final OrderRepository orderRepository;

    private final OrderItemsRepository orderItemsRepository;

    private WarehouseRestTemplate warehouseRestTemplate;

    public Order addNewOrder(Long userId, Map<Long, Integer> items, final UUID transactionId) {

        AtomicBoolean allItemsExists = new AtomicBoolean(true);
        items.forEach((id, quantity) -> {
            if (warehouseRestTemplate.callGetItemById(id, transactionId) == null) {
                allItemsExists.set(false);
            }
        });

        // todo: this should be a transaction
        if (allItemsExists.get()) {
            var newOrder = orderRepository.save(new Order(userId));
            items.forEach((id, quantity) -> {
                var orderItemPK = new OrderItemPK(newOrder.getId(), id);
                var item = new OrderItems(orderItemPK, quantity);
                orderItemsRepository.save(item);
            });
            return newOrder;
        } else {
            return null;
        }
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }
}
