package com.example.demo.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Cart {

   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int cartId;



    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<ItemModel> itemModel=new ArrayList<ItemModel>();


    @JsonManagedReference
    @OneToOne(cascade=CascadeType.ALL)
    private Customer customer;

    public Cart(int cartId, List<ItemModel> itemModel, Customer customer) {
        this.cartId = cartId;
        this.itemModel = itemModel;
        this.customer = customer;
    }

    public Cart()
    {}

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @JsonIgnore
    public List<ItemModel> getItemModel() {
        return itemModel;
    }

    public void setItemModel(List<ItemModel> itemModel) {
        this.itemModel = itemModel;
    }



    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }


}

