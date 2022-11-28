package com.fooddelivey.project.service;

import com.fooddelivey.project.model.User;
import com.fooddelivey.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticateUser(User user) {
        Optional<User> foundUser = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (foundUser.isPresent())
            return foundUser.get();
        throw new IllegalArgumentException("User incorect!");
    }

    public boolean checkIfAdmin(String email, String password) {
        return email.equals("admin") && password.equals("admin");
    }
}
