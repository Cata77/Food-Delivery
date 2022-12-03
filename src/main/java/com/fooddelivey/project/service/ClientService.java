package com.fooddelivey.project.service;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.model.User;
import com.fooddelivey.project.view.ClientView;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientView clientView;
    private final OrderService orderService;
    private final Order order;

    public ClientService(ClientView clientView, OrderService orderService, Order order) {
        this.clientView = clientView;
        this.orderService = orderService;
        this.order = order;
    }

    public void showClientInfo(User user) {
        clientView.showUserDetails(user);
        boolean enoughFunds = false;
        while (!enoughFunds) {
            createOrder();
            enoughFunds = orderService.checkBalance(user, order);
        }
        orderService.submitOrder(user,order);
    }

    private void createOrder() {
        String response = "y";
        while (response.equals("y")) {
            clientView.showMenu();
            Food food = clientView.selectFood();
            int pieces = clientView.readPieces();
            orderService.initializeOrder(order, food, pieces);
            clientView.showCartDetails(order);
            response = clientView.askClientToContinue();
        }
    }
}
