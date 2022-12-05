package com.fooddelivey.project.service;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.model.OrderItem;
import com.fooddelivey.project.model.User;
import com.fooddelivey.project.repository.OrderItemRepository;
import com.fooddelivey.project.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DBService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    public DBService(OrderRepository orderRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
    }

    public Order findMostExpensiveOrder(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().max(Comparator.comparing(Order::getTotalPrice)).orElseThrow(NoSuchElementException::new);
    }

    public Food findMostExpensiveFood() {
        Map<Food,Integer> foodMap = new HashMap<>();
        List<OrderItem> orderItems = orderItemRepository.findAll();
        orderItems.forEach(orderItem -> foodMap.put(orderItem.getFood(),orderItem.getPieces()));
        return Collections.max(foodMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public User findUserWhoOrderedMost() {
        Map<Integer,Integer> idPiecesMp = new HashMap<>();
        Map<User,Integer> idUserMap = new HashMap<>();
        List<OrderItem> orderItems = orderItemRepository.findAll();
        orderItems.forEach(orderItem -> idPiecesMp.put(orderItem.getOrder().getId(),idPiecesMp.getOrDefault(orderItem.getOrder().getId(),0) + orderItem.getPieces()));
        mapUserToNumberOfOrderedPieces(idPiecesMp, idUserMap);
        return Collections.max(idUserMap.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    private void mapUserToNumberOfOrderedPieces(Map<Integer, Integer> idPiecesMp, Map<User, Integer> idUserMap) {
        for (Map.Entry<Integer, Integer> entry : idPiecesMp.entrySet()) {
            Order orderById = orderRepository.findOrderById(entry.getKey());
            idUserMap.put(orderById.getUser(), entry.getValue());
        }
    }

    public double calculateTotalIncome() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(Order::getTotalPrice).reduce(0.0,Double::sum);
    }
}
