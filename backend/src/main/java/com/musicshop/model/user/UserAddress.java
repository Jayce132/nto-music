package com.musicshop.model.user;

import com.musicshop.model.BaseModel;
import com.musicshop.model.address.Address;

import javax.persistence.*;

@Entity
@Table(name = "user_addresses")
public class UserAddress extends BaseModel<Long> {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}