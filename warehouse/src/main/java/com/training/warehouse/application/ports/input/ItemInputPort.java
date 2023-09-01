package com.training.warehouse.application.ports.input;

import com.training.warehouse.application.usecases.ItemUseCase;
import com.training.warehouse.domain.entity.Item;
import com.training.warehouse.infrastructure.persistence.ItemRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemInputPort implements ItemUseCase {

    private final ItemRepository itemRepository;

    public ItemInputPort(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item addItem(String title, String description, BigDecimal price) {
        var newItem = new Item(title, description, price);
        return itemRepository.save(newItem);
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }
}
