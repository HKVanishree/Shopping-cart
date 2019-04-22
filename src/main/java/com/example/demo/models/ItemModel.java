package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@Entity
public class ItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    private int quantity;

    @JsonIgnore
    @JsonManagedReference
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "productId",nullable = true,columnDefinition="integer")
    private Product product;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cartId",nullable = true,columnDefinition="integer")
    private Cart cart;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId",nullable = true,columnDefinition="integer")
    private OrderModel orderModel;



    public ItemModel(int itemId, Product product, int quantity, Cart cart, OrderModel orderModel) {
        this.itemId = itemId;
        this.product = product;
        this.quantity = quantity;
        this.cart = cart;
        this.orderModel = orderModel;
    }

    public ItemModel(){}

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public OrderModel getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(OrderModel orderModel) {
        this.orderModel = orderModel;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }


}
