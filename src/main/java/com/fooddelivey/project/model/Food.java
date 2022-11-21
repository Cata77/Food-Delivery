package com.fooddelivey.project.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Component
@Table(name = "Food")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Category category;
    private double calories;

    public Food() {
    }

    public Food(Integer id, String name, Category category, double calories) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Food food = (Food) o;
        return Double.compare(food.calories, calories) == 0 && Objects.equals(id, food.id) && Objects.equals(name, food.name) && category == food.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, calories);
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", calories=" + calories +
                '}';
    }
}
