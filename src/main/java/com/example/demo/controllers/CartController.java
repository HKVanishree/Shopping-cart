package com.example.demo.controllers;

import com.example.demo.models.Cart;
import com.example.demo.models.OrderModel;
import com.example.demo.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @RequestMapping(value = "/cart/", method = POST)
    public void createCart(@RequestBody Cart cart) {
        cartService.NewCart(cart);
    }

    @RequestMapping(value = "/cart/", method = GET)
    public List<Cart> allitems() {
        return cartService.getCart();
    }

    @RequestMapping(value = "/cart/{id}/", method = DELETE)
    public void cartDelete(@PathVariable Integer id) {
        cartService.deleteCart(id);
    }

    @RequestMapping(value = "/cart/addItem/{custId}/",method = POST)
    public String  addItemToCart(@PathVariable int custId,@RequestBody Integer productId){
       if(cartService.addToCart(custId,productId)==null)
           return "Out of Stock";
       return "Product added sucessfully to cart";
    }
}
