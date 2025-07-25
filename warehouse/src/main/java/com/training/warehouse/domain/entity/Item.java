package com.training.warehouse.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue
    public Long id;
    public String title;
    public String description;
    public BigDecimal price;

    public Item(String title, String description, BigDecimal price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
