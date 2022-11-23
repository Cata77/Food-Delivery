package com.fooddelivey.project;

import com.fooddelivey.project.model.User;
import com.fooddelivey.project.service.UserService;
import com.fooddelivey.project.view.ClientView;
import org.springframework.stereotype.Component;

@Component
public class FoodDelivery {

    private final ClientView clientView;
    private final UserService userService;

    public FoodDelivery(ClientView clientView, UserService userService) {
        this.clientView = clientView;
        this.userService = userService;
    }

    public void run() {
        clientView.printWelcomeMessage();
        User user = userService.authenticateUser(clientView.readCredentials());
        clientView.showUserDetails(user);

    }
}
