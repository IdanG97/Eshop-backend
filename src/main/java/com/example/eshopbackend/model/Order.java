package com.example.eshopbackend.model;

import jakarta.persistence.*;
import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // קישור למשתמש שהזמין
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // כל הפריטים בהזמנה
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id") // foreign key בטבלת order_items
    private List<OrderItem> items;

    private String address;
    private double total;
    private String status; // TEMP, CLOSE וכו'
    private LocalDateTime date;

    public Order() {}

    public Order(User user, List<OrderItem> items, String address, double total, String status, LocalDateTime date) {
        this.user = user;
        this.items = items;
        this.address = address;
        this.total = total;
        this.status = status;
        this.date = date;
    }

    // Getters & Setters (אפשר עם Alt+Insert באינטליג'י)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
}
