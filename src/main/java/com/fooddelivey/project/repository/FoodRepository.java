package com.fooddelivey.project.repository;

import com.fooddelivey.project.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}
