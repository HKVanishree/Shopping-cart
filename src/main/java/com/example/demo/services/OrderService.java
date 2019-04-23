package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Customer;
import com.example.demo.models.ItemModel;
import com.example.demo.models.OrderModel;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    static Logger logger = Logger.getLogger(OrderService.class);


    @Autowired
    OrderRepository orderRepository;



    @Autowired
    CustomerService customerService;

    @Autowired
    CartRepository cartRepository;

    public void newOrder(OrderModel orderModel,int customerId)
    {

        Customer customer=customerService.getCustomerById(customerId);
        Cart cart= customer.getCart();
        orderModel.setCustomer(customer);
        orderRepository.save(orderModel);
        List<ItemModel> itemModelList= cart.getItemModel();
        logger.info("Transferring items from cart to order");
        for(ItemModel itemModel:itemModelList) {
            itemModel.setCart(null);
            orderModel.addItems(itemModel);
        }
        logger.info("Order placed");
    }

    public List<OrderModel> getOrders()
    {
        List<OrderModel> orderModelList = new ArrayList<>();
        orderRepository.findAll()
                .forEach(orderModelList::add);
        return orderModelList;
    }

    public void deleteOrder(int id)
    {
        orderRepository.deleteById(id);
    }


}
