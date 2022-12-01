package com.fooddelivey.project;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.model.User;
import com.fooddelivey.project.service.AdminService;
import com.fooddelivey.project.service.OrderService;
import com.fooddelivey.project.service.UserService;
import com.fooddelivey.project.view.ClientView;
import org.springframework.stereotype.Component;

@Component
public class FoodDelivery {

    private final ClientView clientView;
    private final UserService userService;
    private final AdminService adminService;
    private final OrderService orderService;
    private final Order order;

    public FoodDelivery(ClientView clientView, UserService userService, AdminService adminService, OrderService orderService, Order order) {
        this.clientView = clientView;
        this.userService = userService;
        this.adminService = adminService;
        this.orderService = orderService;
        this.order = order;
    }

    public void run() {
        clientView.printWelcomeMessage();
        User user = userService.authenticateUser(clientView.readCredentials());
        if (userService.checkIfAdmin(user.getEmail(), user.getPassword()))
            adminService.showAdminInfo();
        else {
            clientView.showUserDetails(user);

            clientView.showMenu();
            Food food = clientView.selectFood();
            int pieces = clientView.readPieces();
            orderService.updateOrder(order,food, pieces);
            clientView.showCartDetails(order);
        }
    }
}
