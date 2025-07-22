package com.training.order.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_orders")
@NoArgsConstructor
@Getter
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    public Order(Long userId) {
        this.userId = userId;
    }
}
