package com.musicshop.repository.order;

import com.musicshop.model.order.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<CustomerOrder, Long> {
    // Implement specific methods for Order
}
