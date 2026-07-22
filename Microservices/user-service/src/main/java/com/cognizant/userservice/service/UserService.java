package com.cognizant.userservice.service;

import com.cognizant.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new ArrayList<>();

    public UserService() {
        users.add(new User(1L, "Alice Smith", "alice@example.com"));
        users.add(new User(2L, "Bob Johnson", "bob@example.com"));
        users.add(new User(3L, "Charlie Brown", "charlie@example.com"));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
