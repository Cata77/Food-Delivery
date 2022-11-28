package com.fooddelivey.project.service;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
    private final List<Food> foodList = new ArrayList<>();
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getFoodList() {
        return foodRepository.findAll();
    }
}
