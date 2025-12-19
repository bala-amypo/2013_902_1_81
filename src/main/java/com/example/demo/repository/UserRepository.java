package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Used for authentication and checking email uniqueness during registration
    Optional<User> findByEmail(String email);
    
    // Helper to quickly check if an email exists without loading the full entity
    boolean existsByEmail(String email);
}