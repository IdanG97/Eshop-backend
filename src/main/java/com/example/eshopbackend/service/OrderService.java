package com.example.eshopbackend.service;

import com.example.eshopbackend.model.Order;
import com.example.eshopbackend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepo;
    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<Order> getOrdersByUser(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    public Order getOrder(Long id) {
        return orderRepo.findById(id).orElse(null);
    }

    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }

    public boolean deleteOrder(Long id) {
        if (!orderRepo.existsById(id)) return false;
        orderRepo.deleteById(id);
        return true;
    }
}
