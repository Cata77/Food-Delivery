package com.fooddelivey.project.service;

import com.fooddelivey.project.model.Food;
import com.fooddelivey.project.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodService {
    private final Map<String,Integer> foodPriceMap = new HashMap<>();
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getFoodList() {
        return foodRepository.findAll();
    }

    public Map<String,Integer> getFoodPriceMap() {
        List<Food> foodList = getFoodList();
        foodList.forEach(food -> foodPriceMap.put(food.getName(),food.getPrice()));
        return foodPriceMap;
    }
}
