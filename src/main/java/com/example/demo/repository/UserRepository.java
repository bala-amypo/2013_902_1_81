package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query to find user by email for JWT authentication
    Optional<User> findByEmail(String email);
    
    // Check if email exists for registration validation
    boolean existsByEmail(String email);
}