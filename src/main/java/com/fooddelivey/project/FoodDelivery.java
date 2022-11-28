package com.fooddelivey.project;

import com.fooddelivey.project.model.User;
import com.fooddelivey.project.service.AdminService;
import com.fooddelivey.project.service.UserService;
import com.fooddelivey.project.view.ClientView;
import org.springframework.stereotype.Component;

@Component
public class FoodDelivery {

    private final ClientView clientView;
    private final UserService userService;
    private final AdminService adminService;

    public FoodDelivery(ClientView clientView, UserService userService, AdminService adminService) {
        this.clientView = clientView;
        this.userService = userService;
        this.adminService = adminService;
    }

    public void run() {
        clientView.printWelcomeMessage();
        User user = userService.authenticateUser(clientView.readCredentials());
        if (userService.checkIfAdmin(user.getEmail(), user.getPassword()))
            adminService.showAdminInfo();
        else {
            clientView.showUserDetails(user);
            clientView.showMenu();
        }
    }
}
