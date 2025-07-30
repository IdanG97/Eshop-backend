package com.example.eshopbackend.repository;

import com.example.eshopbackend.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    // אפשר להוסיף חיפושים נוספים בהמשך
}
