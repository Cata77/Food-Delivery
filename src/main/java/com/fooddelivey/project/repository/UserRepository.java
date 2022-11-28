package com.fooddelivey.project.repository;

import com.fooddelivey.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserByEmailAndPassword(String email, String password);
}
