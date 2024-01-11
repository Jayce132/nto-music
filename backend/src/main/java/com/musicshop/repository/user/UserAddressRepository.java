package com.musicshop.repository.user;

import com.musicshop.model.user.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
    // Implement specific methods for CustomerAddress
}