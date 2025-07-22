package com.training.order.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.order.application.usecases.OrderUseCase;
import com.training.order.domain.model.Order;
import com.training.order.domain.model.OrderItemPK;
import com.training.order.domain.model.OrderItems;
import com.training.order.infrastructure.adapter.WarehouseRestTemplate;
import com.training.order.infrastructure.repository.OrderItemsRepository;
import com.training.order.infrastructure.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService implements OrderUseCase {

    private final OrderRepository orderRepository;

    private final OrderItemsRepository orderItemsRepository;

    private final WarehouseRestTemplate warehouseRestTemplate;

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Order addNewOrder(Long userId, Map<Long, Integer> items) {

        AtomicBoolean allItemsExists = new AtomicBoolean(true);
        items.forEach((id, quantity) -> {
            if (warehouseRestTemplate.callGetItemById(id) == null) {
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

                // Send a message to Kafka for each item to add loyalty points
                try {
                    // Create a map with the purchase details
                    Map<String, Object> purchaseEvent = new HashMap<>();
                    purchaseEvent.put("userId", userId.toString());
                    purchaseEvent.put("itemId", id.toString());
                    purchaseEvent.put("quantity", quantity);
                    purchaseEvent.put("orderId", newOrder.getId().toString());

                    // Convert the map to JSON
                    String message = objectMapper.writeValueAsString(purchaseEvent);

                    // Send the message to Kafka
                    kafkaTemplate.send("purchase-events", message);

                    log.info("Sent purchase event to Kafka: {}", message);
                } catch (JsonProcessingException e) {
                    log.error("Error sending purchase event to Kafka: {}", e.getMessage(), e);
                }
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
