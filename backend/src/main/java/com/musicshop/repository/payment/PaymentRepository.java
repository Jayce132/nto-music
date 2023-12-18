package com.musicshop.repository.payment;

import com.musicshop.model.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Implement specific methods for Payment
}
