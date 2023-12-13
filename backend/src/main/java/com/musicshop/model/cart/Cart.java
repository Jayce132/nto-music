package com.musicshop.model.cart;

import com.musicshop.model.BaseModel;

import java.time.LocalDateTime;

public class Cart extends BaseModel<Long> {
    private Long customerId;
    private LocalDateTime dateCreated;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}