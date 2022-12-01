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
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<OrderItem> orderItemList;
    private LocalDateTime localDateTime;
    private double totalPrice;

    public Order() {
    }

    public Order(Integer id, List<OrderItem> orderItemList, LocalDateTime localDateTime) {
        this.id = id;
        this.orderItemList = orderItemList;
        this.localDateTime = localDateTime;
        this.totalPrice = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
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
        return Double.compare(order.totalPrice, totalPrice) == 0 && Objects.equals(id, order.id) && Objects.equals(orderItemList, order.orderItemList) && Objects.equals(localDateTime, order.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderItemList, localDateTime, totalPrice);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderItemList=" + orderItemList +
                ", localDateTime=" + localDateTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
