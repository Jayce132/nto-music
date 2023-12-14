package com.musicshop.repository.customer;

import com.musicshop.model.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Implement specific methods for Customer
}
