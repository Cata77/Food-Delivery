package com.fooddelivey.project.service;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.model.OrderItem;
import com.fooddelivey.project.model.User;
import com.fooddelivey.project.view.ClientView;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final FoodService foodService;
    private final ClientView clientView;
    private final List<OrderItem> orderItems = new ArrayList<>();

    public OrderService(FoodService foodService, ClientView clientView) {
        this.foodService = foodService;
        this.clientView = clientView;
    }

    public void updateOrder(Order order,Food food, int pieces) {
        if (pieces == 0)
            removeFoodIfPresent(order,food);
        else {
            addItemToOrder(order, food, pieces);
        }
    }

    private void addItemToOrder(Order order, Food food, int pieces) {
        int price = foodService.getFoodPriceMap().get(food.getName()) * pieces;
        OrderItem orderItem = new OrderItem();
        orderItem.setFood(food);
        orderItem.setPieces(pieces);
        orderItem.setPrice(price);
        orderItems.add(orderItem);
        order.setTotalPrice(order.getTotalPrice() + price);
        order.setOrderItemList(orderItems);
        clientView.showLastItemAdded(order);
    }

    public void removeFoodIfPresent(Order order, Food food) {
        Optional<OrderItem> foodToRemove = orderItems.stream().filter(orderItem -> orderItem.getFood().equals(food)).findFirst();
        if (foodToRemove.isEmpty())
            System.out.println("Nu ati comandat o astfel de mancare!");
        else {
            System.out.println("Am eliminat mancarea selectata din comanda!");
            order.setTotalPrice(order.getTotalPrice()-foodToRemove.get().getPrice());
            orderItems.remove(foodToRemove.get());
            order.setOrderItemList(orderItems);
        }
    }

    public boolean checkBalance(User user, Order order) {
        if (order.getTotalPrice() >= user.getBalance()) {
            System.out.println("\nNu ai suficienti bani, balanta ta este de doar " + user.getBalance() +
                    " RON. Te rog modifica comada petru a corespunde bugetului tau!");
            return false;
        }
        return true;
    }

}
