package com.fooddelivey.project.service;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.model.OrderItem;
import com.fooddelivey.project.model.User;
import com.fooddelivey.project.repository.OrderRepository;
import com.fooddelivey.project.repository.UserRepository;
import com.fooddelivey.project.view.ClientView;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final FoodService foodService;
    private final ClientView clientView;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final List<OrderItem> orderItems = new ArrayList<>();

    public OrderService(FoodService foodService, ClientView clientView, OrderRepository orderRepository, UserRepository userRepository) {
        this.foodService = foodService;
        this.clientView = clientView;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public void initializeOrder(Order order, Food food, int pieces) {
        if (pieces == 0)
            removeFood(order,food);
        else {
            orderCreation(order, food, pieces);
        }
    }

    private void orderCreation(Order order, Food food, int pieces) {
        int price = foodService.getFoodPriceMap().get(food.getName()) * pieces;
        Optional<OrderItem> foodIsAlreadyPresent = checkIfFoodIsAlreadyPresent(food);
        boolean newOrderCreated = false;
        if (foodIsAlreadyPresent.isPresent()){
            foodIsAlreadyPresent.get().setPieces(pieces);
            foodIsAlreadyPresent.get().setPrice(price);
        } else {
            createNewOrderItem(food, pieces, price);
            newOrderCreated = true;
        }
        order.setTotalPrice(calculateTotalPrice());
        order.setOrderItemList(orderItems);
        if (newOrderCreated)
            clientView.showLastItemAdded(order);
        else clientView.showModifiedChange();
    }

    private void createNewOrderItem(Food food, int pieces, int price) {
        OrderItem orderItem = new OrderItem();
        orderItem.setFood(food);
        orderItem.setPieces(pieces);
        orderItem.setPrice(price);
        orderItems.add(orderItem);
    }

    public Optional<OrderItem> checkIfFoodIsAlreadyPresent(Food food) {
        return orderItems.stream().filter(orderItem -> orderItem.getFood().equals(food)).findFirst();
    }

    public void removeFood(Order order, Food food) {
        Optional<OrderItem> foodToRemove = orderItems.stream().filter(orderItem -> orderItem.getFood().equals(food)).findFirst();
        if (foodToRemove.isEmpty())
            System.out.println("You have not ordered such a meal!");
        else {
            System.out.println("We have removed the selected food from the order!");
            order.setTotalPrice(order.getTotalPrice()-foodToRemove.get().getPrice());
            orderItems.remove(foodToRemove.get());
            order.setOrderItemList(orderItems);
        }
    }

    public boolean checkBalance(User user, Order order) {
        if (order.getTotalPrice() >= user.getBalance()) {
            System.out.println("\nYou don't have enough money, your balance is " + user.getBalance() +
                    " EUR. We do not empty your cart, please remove some of the items.");
            return false;
        }
        return true;
    }

    public double calculateTotalPrice() {
        return orderItems.stream().map(OrderItem::getPrice).reduce(0.0,Double::sum);
    }

    public void submitOrder(User user,Order order) {
        order.setLocalDateTime(LocalDateTime.now());
        order.setUser(user);
        orderRepository.save(order);
        user.setBalance(user.getBalance()-order.getTotalPrice());
        userRepository.save(user);
        clientView.showCompleteOrder(order);
    }
}
