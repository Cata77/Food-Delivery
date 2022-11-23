package com.fooddelivey.project.view;

import com.fooddelivey.project.model.User;
import com.fooddelivey.project.service.AdminService;
import com.fooddelivey.project.service.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class ClientView {
    private final Scanner scanner = new Scanner(System.in);
    private final User user;

    public ClientView(User user) {
        this.user = user;
    }

    public void printWelcomeMessage() {
        System.out.println("Bine ati venit in aplicatia noastra! Va rugam sa va autentificati!");
    }

    public User readCredentials() {
        System.out.println("Adresa de email:");
        user.setEmail(scanner.nextLine());
        System.out.println("Parola: ");
        user.setPassword(scanner.nextLine());
        return user;
    }

    public void showUserDetails(User user) {
        System.out.println("Buna " + user.getUserName() + ", balanta ta este de " + user.getBalance() + " RON");
    }
}
