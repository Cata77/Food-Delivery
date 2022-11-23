package com.fooddelivey.project.service;

import com.fooddelivey.project.model.User;
import com.fooddelivey.project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AdminService adminService;

    public UserService(UserRepository userRepository, AdminService adminService) {
        this.userRepository = userRepository;
        this.adminService = adminService;
    }

    public User authenticateUser(User user) {
        Optional<User> optionalUser = userRepository.findUserByEmailAndPassword(user.getEmail(), user.getPassword());
        if (optionalUser.isPresent()) {
            if (checkIfAdmin(user.getEmail(), user.getPassword()))
                adminService.showAdminInfo();
            return user;
        }
        throw new IllegalArgumentException("User incorect!");
    }

    public boolean checkIfAdmin(String email, String password) {
        return email.equals("admin") && password.equals("admin");
    }
}
