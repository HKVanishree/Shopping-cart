package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ItemRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    static Logger logger = Logger.getLogger(CartService.class);

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ItemService itemService;

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;

    @Autowired
    ItemRepository itemRepository;

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
        logger.warn("Cart with cart Id "+id+" deleted\n");
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
               ItemModel itemModel1= itemService.addNewItem(itemModel);
               if(itemModel1 == null) {
                   logger.error("Requested item is *Out of stock*");
                   return null;
               }
               int bill=itemModel.getProduct().getPrice();
               customer.setBill(customer.getBill()+bill);
               customerService.editCustomer(customer,customerId);
                flag=1;


            }
        }
        if(flag==0) {
            ItemModel itemModel = new ItemModel();
            itemModel.setQuantity(1);
            itemModel.setCart(cart);
            Product product = productService.getProductById(productId);
            itemModel.setProduct(product);
           ItemModel itemModel1= itemService.addNewItem(itemModel);
            List<ItemModel> itemModelList = cart.getItemModel();
            itemModelList.add(itemModel);
            if(itemModel1!=null)
            cart.setItemModel(itemModelList);
            else {
                logger.error("Requested item is *Out of stock*");
                return null;
            }

            int bill=itemModel.getProduct().getPrice();
            customer.setBill(customer.getBill()+bill);
            customerService.editCustomer(customer,customerId);
        }

     logger.warn("Cart updated\n");

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



