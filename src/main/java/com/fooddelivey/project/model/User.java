package com.fooddelivey.project.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Component
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private Order order;

    public User() {
    }

    public User(Integer id, String userName, String password, Order order) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(order, user.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, order);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", order=" + order +
                '}';
    }
}
