package com.example.eshopbackend.service;

import com.example.eshopbackend.model.Item;
import com.example.eshopbackend.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepo;
    public ItemService(ItemRepository itemRepo) {
        this.itemRepo = itemRepo;
    }

    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    public Item getItem(Long id) {
        return itemRepo.findById(id).orElse(null);
    }

    public Item saveItem(Item item) {
        return itemRepo.save(item);
    }

    public boolean deleteItem(Long id) {
        if (!itemRepo.existsById(id)) return false;
        itemRepo.deleteById(id);
        return true;
    }
}
