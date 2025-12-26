package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.exception.ValidationException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ValidationException("Email already in use");
        }

        if (user.getPassword() == null || user.getPassword().length() < 8) {
            throw new ValidationException("Password must be at least 8 characters");
        }

        if (user.getDepartment() == null) {
            throw new ValidationException("Department is required");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");

        return userRepository.save(user);
    }
}
