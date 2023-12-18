package com.musicshop.repository.address;

import com.musicshop.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // Implement specific methods for Address
}
