package com.fooddelivey.project;

import com.fooddelivey.project.view.ClientView;
import org.springframework.stereotype.Component;

@Component
public class FoodDelivery {

    private final ClientView clientView;

    public FoodDelivery(ClientView clientView) {
        this.clientView = clientView;
    }

    public void run() {
        clientView.printWelcomeMessage();
    }
}
