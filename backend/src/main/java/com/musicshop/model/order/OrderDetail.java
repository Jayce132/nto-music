package com.musicshop.model.order;

import com.musicshop.model.BaseModel;
import com.musicshop.model.product.Product;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail extends BaseModel<Long> {
    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private UserOrder order;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    public UserOrder getOrder() {
        return order;
    }

    public void setOrder(UserOrder order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
