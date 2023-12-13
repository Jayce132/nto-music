package com.musicshop.model.payment;

import com.musicshop.model.BaseModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment extends BaseModel<Long> {
    private Long orderID;
    private String paymentMethod;
    private LocalDateTime paymentDate;
    private BigDecimal amount;

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
