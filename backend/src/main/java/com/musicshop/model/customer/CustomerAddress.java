package com.musicshop.model.customer;

import com.musicshop.model.BaseModel;
import com.musicshop.model.address.Address;

import javax.persistence.*;

@Entity
public class CustomerAddress extends BaseModel<Long> {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}