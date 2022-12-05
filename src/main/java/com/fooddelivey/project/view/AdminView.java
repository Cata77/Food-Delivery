package com.fooddelivey.project.view;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.model.User;
import org.springframework.stereotype.Service;

@Service
public class AdminView {

    public void showAdminWelcomeMessage() {
        System.out.println("\nBine ai venit la panoul Admin Food Delivery Service!");
    }

    public void showMostExpensiveOrder(Order order) {
        System.out.println("\nCea mai scumpa comanda a este #" + order.getId() +
                " cu un pret total de " + order.getTotalPrice() + " RON");
    }

    public void showMostOrderedFood(Food food) {
        System.out.println("Cea mai populara mancare: " + food.getName());
    }

    public void showUserWhoOrderedMost(User user) {
        System.out.println("Userul care a comandat cel mai mult este: " + user.getUserName() +
                " [#" + user.getId() + "]");
    }

    public void showTotalIncome(double totalIncome) {
        System.out.println("Venitul total este de " + totalIncome + " RON");
    }
}
