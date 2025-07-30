package com.example.eshopbackend.controller;

import com.example.eshopbackend.model.*;
import com.example.eshopbackend.service.OrderService;
import com.example.eshopbackend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    // פונקציית Checkout — הפיכת עגלה להזמנה
    @PostMapping("/checkout/{userId}")
    public Order checkout(@PathVariable Long userId, @RequestParam String address) {
        User user = userService.getUserById(userId);
        if (user == null) return null;
        List<CartItem> cart = user.getCart();
        if (cart == null || cart.isEmpty()) return null; // אין עגלה

        // ניצור רשימת פריטים להזמנה
        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;
        for (CartItem ci : cart) {
            OrderItem oi = new OrderItem();
            oi.setItem(ci.getItem());
            oi.setQuantity(ci.getQuantity());
            orderItems.add(oi);
            total += ci.getItem().getPrice() * ci.getQuantity();
        }

        Order order = new Order();
        order.setUser(user);
        order.setItems(orderItems);
        order.setAddress(address);
        order.setTotal(total);
        order.setStatus("OPEN");
        order.setDate(LocalDateTime.now());

        // נשמור את ההזמנה
        Order savedOrder = orderService.addOrder(order);

        // מרוקנים את העגלה של המשתמש
        user.getCart().clear();
        userService.saveUser(user);

        return savedOrder;
    }

    // **** הוספתי את המתודה הזו כדי לעבוד עם ה-frontend שלך ****
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        // דואג שיהיה תאריך וסטטוס אם לא נשלח מה-frontend
        order.setDate(LocalDateTime.now());
        if (order.getStatus() == null) order.setStatus("OPEN");
        return orderService.addOrder(order);
    }

    // לדוגמה: שליפת כל ההזמנות
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // שליפת הזמנות לפי מזהה משתמש (כדי להציג "ההזמנות שלי")
    @GetMapping("/by-user/{userId}")
    public List<Order> getOrdersByUser(@PathVariable Long userId) {
        return orderService.getOrdersByUser(userId);
    }
}
