package com.musicshop.service.user;

import com.musicshop.model.user.User;
import com.musicshop.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    public User updateUser(Long userId, User userDetails) {
        return userRepository.findById(userId).map(user -> {
            user.setFirstName(userDetails.getFirstName());
            user.setLastName(userDetails.getLastName());
            user.setEmail(userDetails.getEmail());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            // Will add any other fields that have to be updated
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }
}

