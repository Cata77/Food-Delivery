package com.fooddelivey.project.view;

import com.fooddelivey.project.model.Order;
import org.springframework.stereotype.Service;

@Service
public class AdminView {

    public void showAdminWelcomeMessage() {
        System.out.println("\nBine ai venit la panoul Admin Food Delivery Service!");
    }

    public void showMostExpensiveOrder(Order order) {
        System.out.println("\nCea mai scumpa comanda a fost #" + order.getId() +
                " cu un pret total de " + order.getTotalPrice() + " RON");
    }
}
