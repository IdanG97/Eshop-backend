package com.example.eshopbackend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String img;     // קישור לתמונה
    private double price;
    private int stock;

    public Item() {}

    // קונסטרקטור ליצירת פריטים חדשים (בלי id)
    public Item(String name, String description, String img, double price, int stock) {
        this.name = name;
        this.description = description;
        this.img = img;
        this.price = price;
        this.stock = stock;
    }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
