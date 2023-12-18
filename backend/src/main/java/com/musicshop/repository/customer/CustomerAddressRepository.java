package com.musicshop.repository.customer;

import com.musicshop.model.customer.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Long> {
    // Implement specific methods for CustomerAddress
}