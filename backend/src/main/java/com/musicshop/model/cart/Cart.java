package com.musicshop.model.cart;

import com.musicshop.model.BaseModel;
import com.musicshop.model.user.User;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "carts")
public class Cart extends BaseModel<Long> {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime dateCreated;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
