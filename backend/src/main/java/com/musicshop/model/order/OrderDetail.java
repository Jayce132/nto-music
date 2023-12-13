package com.musicshop.model.order;

import com.musicshop.model.BaseModel;

import java.math.BigDecimal;

public class OrderDetail extends BaseModel<Long> {
    private Long orderID;
    private Long productID;
    private int quantity;
    private BigDecimal priceEach;

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public void setPriceEach(BigDecimal priceEach) {
        this.priceEach = priceEach;
    }
}
