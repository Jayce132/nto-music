package com.musicshop.model.payment;

import com.musicshop.model.BaseModel;
import com.musicshop.model.order.UserOrder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment extends BaseModel<Long> {
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private UserOrder order;

    private String paymentMethod;
    private LocalDateTime paymentDate;
    private BigDecimal amount;

    public UserOrder getOrder() {
        return order;
    }

    public void setOrder(UserOrder order) {
        this.order = order;
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
