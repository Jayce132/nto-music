package com.musicshop.model.cart;

import com.musicshop.model.BaseModel;
import com.musicshop.model.customer.Customer;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
public class Cart extends BaseModel<Long> {

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private LocalDateTime dateCreated;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
