package com.training.warehouse.framework.adapters.input;

import com.training.warehouse.application.ports.input.ItemInputPort;
import com.training.warehouse.domain.entity.Item;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    private final ItemInputPort itemInputPort;

    public ItemController(ItemInputPort itemInputPort) {
        this.itemInputPort = itemInputPort;
    }

    @PostMapping
    public Item addItem(@RequestParam String title, @RequestParam String description, @RequestParam BigDecimal price) {
        return itemInputPort.addItem(title, description, price);
    }

    @GetMapping
    public List<Item> getAll() {
        return itemInputPort.getAll();
    }
}
