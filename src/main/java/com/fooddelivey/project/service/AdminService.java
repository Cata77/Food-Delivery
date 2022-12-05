package com.fooddelivey.project.service;

import com.fooddelivey.project.view.AdminView;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final AdminView adminView;
    private final DBService dbService;

    public AdminService(AdminView adminView, DBService dbService) {
        this.adminView = adminView;
        this.dbService = dbService;
    }

    public void showAdminInfo() {
        adminView.showAdminWelcomeMessage();
        adminView.showMostExpensiveOrder(dbService.findMostExpensiveOrder());
        adminView.showMostOrderedFood(dbService.findMostExpensiveFood());
        adminView.showUserWhoOrderedMost(dbService.findUserWhoOrderedMost());
        adminView.showTotalIncome(dbService.calculateTotalIncome());
    }
}
