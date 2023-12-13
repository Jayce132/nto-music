package com.musicshop.model.order;

import com.musicshop.model.BaseModel;

import java.time.LocalDateTime;

public class Order extends BaseModel<Long> {
    private Long customerID;
    private LocalDateTime orderDate;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
