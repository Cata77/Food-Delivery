package com.fooddelivey.project.service;

import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DBService {
    private final OrderRepository orderRepository;

    public DBService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order findMostExpensiveOrder(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().max(Comparator.comparing(Order::getTotalPrice)).orElseThrow(NoSuchElementException::new);
    }
}
