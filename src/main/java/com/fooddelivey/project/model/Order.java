package com.fooddelivey.project.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Component
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Food> foodList;
    @Column(nullable = false)
    private int pieces;
    private LocalDateTime localDateTime;
    private double totalPrice;

    public Order() {
    }

    public Order(Integer id, List<Food> foodList, int pieces, LocalDateTime localDateTime, double totalPrice) {
        this.id = id;
        this.foodList = foodList;
        this.pieces = pieces;
        this.localDateTime = localDateTime;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public int getPieces() {
        return pieces;
    }

    public void setPieces(int pieces) {
        this.pieces = pieces;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return pieces == order.pieces && Double.compare(order.totalPrice, totalPrice) == 0 && Objects.equals(id, order.id) && Objects.equals(foodList, order.foodList) && Objects.equals(localDateTime, order.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, foodList, pieces, localDateTime, totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", foodList=" + foodList +
                ", pieces=" + pieces +
                ", localDateTime=" + localDateTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
