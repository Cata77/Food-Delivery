package com.fooddelivey.project.view;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.model.Order;
import com.fooddelivey.project.model.OrderItem;
import com.fooddelivey.project.model.User;
import com.fooddelivey.project.service.FoodService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.StringJoiner;

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
        System.out.println("Welcome to our application! Please authenticate!\n");
    }

    public User readCredentials() {
        System.out.println("Email address:");
        this.user.setEmail(scanner.nextLine());
        System.out.println("Password: ");
        this.user.setPassword(scanner.nextLine());
        return user;
    }

    public void showUserDetails(User user) {
        System.out.println("\nHello " + user.getUserName() + ", your balance is " + user.getBalance() + " EUR\n");
        System.out.println("This is the menu of the restaurant:");
    }

    public void showMenu() {
        List<Food> foodList = foodService.getFoodList();
        for (Food food : foodList)
            System.out.println("- " + food.getName() + " " + food.getPrice() + " EUR, " + food.getCategory() + " " + food.getCalories() + " calories");
    }

    public Food selectFood() {
        List<Food> foodList = foodService.getFoodList();
        while (true) {
            System.out.println("\nPlease enter the name of the food you would like to order:");
            String foodName = scanner.nextLine();
            Optional<Food> food = foodList.stream().filter(f -> f.getName().equalsIgnoreCase(foodName)).findFirst();
            if (food.isPresent())
                return food.get();
            System.out.println("Wrong name! Please re-enter the name carefully!");
        }
    }

    public int readPieces() {
        while (true) {
            System.out.println("\nHow many pieces do you want to buy? This input overwrites the old value in the cart, entering zero removes the item completely:");
            int pieces = Integer.parseInt(scanner.nextLine());
            if (pieces >= 0)
                return pieces;
            System.out.println("You entered a negative number!");
        }
    }

    public void showLastItemAdded(Order order) {
        List<OrderItem> orderItems = order.getOrderItemList();
        System.out.println("Added " + orderItems.get(orderItems.size()-1).getPieces() + " pieces" +
                " of " + orderItems.get(orderItems.size()-1).getFood().getName() + " to your order!");
    }

    public void showModifiedChange() {
        System.out.println("The order has been modified!");
    }

    public void showCartDetails(Order order) {
        List<OrderItem> orderItems = order.getOrderItemList();
        System.out.println("The order has " + order.getTotalPrice() + " EUR worth of food: ");
        for (OrderItem orderItem : orderItems)
            System.out.println(orderItem.getFood().getName() + " " + orderItem.getPieces() + " pieces, " +
                    orderItem.getPrice() + " EUR total");
    }

    public String askClientToContinue() {
        System.out.println("\nDo you want to continue ordering? (y/n): ");
        return scanner.nextLine();
    }

    public void showCompleteOrder(Order order) {
        List<OrderItem> orderItems = order.getOrderItemList();
        StringJoiner joiner = new StringJoiner(",");
        orderItems.forEach(item -> joiner.add(item.getFood().getName()));
        System.out.print("\nOrder (food: [" + joiner + "], price: " +  order.getTotalPrice() + " EUR, timestamp: " +
                order.getLocalDateTime().toLocalDate() + ") has been confirmed. Thank you for your purchase!");
    }
}
