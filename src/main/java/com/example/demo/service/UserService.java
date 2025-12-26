package com.example.demo.service;

import com.example.demo.entity.User;

import java.util.List;

public interface UserService {

    User register(User user);

    User getByEmail(String email);

    List<User> getAllUsers();
}
