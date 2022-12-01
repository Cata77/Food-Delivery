package com.fooddelivey.project.service;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.model.OrderItem;
import com.fooddelivey.project.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderItem orderItem;
    private final FoodService foodService;
    private final List<OrderItem> orderItems = new ArrayList<>();

    public OrderService(OrderItem orderItem, FoodService foodService) {
        this.orderItem = orderItem;
        this.foodService = foodService;
    }

    public void updateOrder(Order order,Food food, int pieces) {
        int price = foodService.getFoodPriceMap().get(food.getName()) * pieces;
        orderItem.setFood(food);
        orderItem.setPieces(pieces);
        orderItem.setPrice(price);
        orderItems.add(orderItem);
        order.setOrderItemList(orderItems);
        order.setTotalPrice(order.getTotalPrice() + price);
    }
}
