package com.training.order.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID userId;

    private UUID itemsId;

    private int quantity;

    public Order(UUID userId, UUID itemsId, int quantity) {
        this.userId = userId;
        this.itemsId = itemsId;
        this.quantity = quantity;
    }

    public Order() {

    }
}
