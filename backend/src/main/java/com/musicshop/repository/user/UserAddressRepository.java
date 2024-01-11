package com.musicshop.repository.user;

import com.musicshop.model.user.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {
    // Implement specific methods for UserAddress
}