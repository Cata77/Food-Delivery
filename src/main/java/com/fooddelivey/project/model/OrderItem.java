package com.fooddelivey.project.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Component
@Table(name = "order_Item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Food food;
    private int pieces;
    private double price;

    public OrderItem() {
    }

    public OrderItem(Integer id, Food food, int pieces, double price) {
        this.id = id;
        this.food = food;
        this.pieces = pieces;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return pieces == orderItem.pieces && Double.compare(orderItem.price, price) == 0 && Objects.equals(id, orderItem.id) && Objects.equals(food, orderItem.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, food, pieces, price);
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", food=" + food +
                ", pieces=" + pieces +
                ", price=" + price +
                '}';
    }
}
