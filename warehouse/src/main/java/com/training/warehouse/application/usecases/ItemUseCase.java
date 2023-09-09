package com.training.warehouse.application.usecases;

import com.training.warehouse.domain.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public interface ItemUseCase {

    Item getItemById(Long id);

    Item addItem(String title, String description, BigDecimal price);

    List<Item> getAll();
}
