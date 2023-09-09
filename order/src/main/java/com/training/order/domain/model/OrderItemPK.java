package com.training.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemPK implements Serializable {
    private Long orderId;
    private Long itemId;
}
