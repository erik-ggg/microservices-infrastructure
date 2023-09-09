package com.training.order.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
