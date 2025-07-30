package com.example.eshopbackend.controller;

import com.example.eshopbackend.model.Item;
import com.example.eshopbackend.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // קבלת כל הפריטים
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    // קבלת פריט לפי id
    @GetMapping("/{id}")
    public Item getItem(@PathVariable Long id) {
        return itemService.getItem(id);
    }

    // יצירת פריט חדש
    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.saveItem(item);
    }

    // עדכון פריט
    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item updatedItem) {
        Item item = itemService.getItem(id);
        if (item == null) return null;
        item.setName(updatedItem.getName());
        item.setDescription(updatedItem.getDescription());
        item.setImg(updatedItem.getImg());
        item.setPrice(updatedItem.getPrice());
        item.setStock(updatedItem.getStock());
        return itemService.saveItem(item);
    }

    // מחיקת פריט
    @DeleteMapping("/{id}")
    public boolean deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id);
    }
}
