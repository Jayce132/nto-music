package com.musicshop.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseModel<ID extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}
