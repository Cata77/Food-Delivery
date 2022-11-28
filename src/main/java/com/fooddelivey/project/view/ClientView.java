package com.fooddelivey.project.view;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.User;
import com.fooddelivey.project.service.FoodService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class ClientView {
    private final Scanner scanner = new Scanner(System.in);
    private final User user;
    private final FoodService foodService;

    public ClientView(User user, FoodService foodService) {
        this.user = user;
        this.foodService = foodService;
    }

    public void printWelcomeMessage() {
        System.out.println("Bine ati venit in aplicatia noastra! Va rugam sa va autentificati!\n");
    }

    public User readCredentials() {
        System.out.println("Adresa de email:");
        this.user.setEmail(scanner.nextLine());
        System.out.println("Parola: ");
        this.user.setPassword(scanner.nextLine());
        return user;
    }

    public void showUserDetails(User user) {
        System.out.println("\nBuna " + user.getUserName() + ", balanta ta este de " + user.getBalance() + " RON\n");
        System.out.println("Acesta este meniul restaurantului:");
    }

    public void showMenu() {
        List<Food> foodList = foodService.getFoodList();
        for (Food food : foodList)
            System.out.println("- " + food.getName() + " " + food.getPrice() + " RON, " + food.getCategory() + " " + food.getCalories() + " calorii");
    }

    public Food selectFood() {
        List<Food> foodList = foodService.getFoodList();
        while (true) {
            System.out.println("\nIntrodu numele mancarii pe care ai dori sa o comanzi:");
            String foodName = scanner.nextLine();
            Optional<Food> food = foodList.stream().filter(f -> f.getName().equalsIgnoreCase(foodName)).findFirst();
            if (food.isPresent())
                return food.get();
            System.out.println("Nume gresit! Te rog reintrodu numele cu atentie!");
        }
    }

    public int readPieces() {
        while (true) {
            System.out.println("\nCate bucati doriti sa comandati? Acest input suprascrie vechea valoare din cart, introducand zero sterge mancarea dorita:");
            int pieces = Integer.parseInt(scanner.nextLine());
            if (pieces > 0)
                return pieces;
            System.out.println("Ai introdus un numar negativ!");
        }
    }
}
