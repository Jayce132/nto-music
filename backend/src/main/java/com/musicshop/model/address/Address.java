package com.musicshop.model.address;

import com.musicshop.model.BaseModel;
import com.musicshop.model.user.UserAddress;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "addresses")
public class Address extends BaseModel<Long> {
    private String street;
    private String number;
    private String postalCode;
    private String city;
    private String country;

    @OneToMany(mappedBy = "address")
    private Set<UserAddress> userAddresses;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(Set<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }
}
