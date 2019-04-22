package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Customer;
import com.example.demo.models.ItemModel;
import com.example.demo.models.OrderModel;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

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
        List<ItemModel> itemModelList= cart.getItemModel();
        orderModel.setCustomer(customer);
        //orderModel.setItemModel(itemModelList);
        orderRepository.save(orderModel);
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
