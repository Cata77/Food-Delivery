package com.fooddelivey.project.view;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.model.User;
import org.springframework.stereotype.Service;

@Service
public class AdminView {

    public void showAdminWelcomeMessage() {
        System.out.println("\nWelcome to the Admin Food Delivery Service board!");
    }

    public void showMostExpensiveOrder(Order order) {
        System.out.println("\nThe most expensive order is #" + order.getId() +
                " with a total price of " + order.getTotalPrice() + " EUR");
    }

    public void showMostOrderedFood(Food food) {
        System.out.println("The most popular food: " + food.getName());
    }

    public void showUserWhoOrderedMost(User user) {
        System.out.println("User that ordered the most is: " + user.getUserName() +
                " [#" + user.getId() + "]");
    }

    public void showTotalIncome(double totalIncome) {
        System.out.println("The total income is " + totalIncome + " EUR");
    }
}
