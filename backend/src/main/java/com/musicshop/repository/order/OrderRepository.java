package com.musicshop.repository.order;

import com.musicshop.model.order.UserOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<UserOrder, Long> {
    // Implement specific methods for Order
}
