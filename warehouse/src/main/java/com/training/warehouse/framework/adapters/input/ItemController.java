package com.training.warehouse.framework.adapters.input;

import com.training.warehouse.application.ports.input.ItemInputPort;
import com.training.warehouse.domain.entity.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
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

    @GetMapping("/{itemId}")
    public Item getItemById(@PathVariable Long itemId) {
        return itemInputPort.getItemById(itemId);
    }

    @GetMapping("/all")
    public List<Item> getAll() {

        log.info("Getting all items");
        return itemInputPort.getAll();
    }
}
