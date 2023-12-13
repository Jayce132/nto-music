package com.musicshop.model.customer;

import com.musicshop.model.BaseModel;

public class CustomerAddress extends BaseModel<Long> {
    private Long customerID;
    private Long addressID;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public Long getAddressID() {
        return addressID;
    }

    public void setAddressID(Long addressID) {
        this.addressID = addressID;
    }
}