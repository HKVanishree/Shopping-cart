package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    public void NewCart(Cart cart)
    {
        cartRepository.save(cart);
        //cartRepository.delete(Cart(orderModel));
    }

    public List<Cart> getCart()
    {
        List<Cart> cartList = new ArrayList<>();
        cartRepository.findAll()
                .forEach(cartList::add);
        return cartList;
    }

    public void deleteCart(int id)
    {
        cartRepository.deleteById(id);
    }

    public Cart addToCart(int customerId,int productId)
    {
        Customer customer = customerService.getCustomerById(customerId);
        Cart cart =customer.getCart();
        int flag=0;
        List<ItemModel> itemModels=cart.getItemModel();
        for (ItemModel itemModel : itemModels) {
            if (itemModel.getProduct().getProductId() == productId) {
                int qty = itemModel.getQuantity();
                qty++;
                itemModel.setQuantity(qty);
                itemRepository.save(itemModel);
                flag=1;

            }
        }
        if(flag==0) {
            ItemModel itemModel = new ItemModel();
            itemModel.setQuantity(1);
            itemModel.setCart(cart);
            Product product = productService.getProductById(productId);
            itemModel.setProduct(product);
            itemRepository.save(itemModel);
            List<ItemModel> itemModelList = cart.getItemModel();
            itemModelList.add(itemModel);
            cart.setItemModel(itemModelList);
        }

        return cart;
    }

    public Cart getCartById(int id) {
        List<Cart> cartList = new ArrayList<>();
        cartRepository.findAll()
                .forEach(cartList::add);
        for (Cart cart : cartList) {
            if (cart.getCartId() == id) {
                return cart;
            }


        }

        return null;

    }



}



