package com.musicshop.repository.user;

import com.musicshop.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<User, Long> {
    User findByEmail(String currentUsername);
    // Implement specific methods for User
}
