package com.training.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OrderItems {

    @EmbeddedId
    private OrderItemPK id;

    private int quantity;
}
