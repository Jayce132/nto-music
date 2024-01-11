package com.musicshop.repository.user;

import com.musicshop.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String currentUsername);
}
