package com.example.demo.controllers;

import com.example.demo.models.OrderModel;
import com.example.demo.models.Product;
import com.example.demo.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/{customerId}", method = POST)
    public void placeOrder(@RequestBody OrderModel orderModel,@PathVariable Integer customerId) {
        orderService.newOrder(orderModel,customerId);
    }

    @RequestMapping(value = "/order/", method = GET)
    public List<OrderModel> allOrders() {
        return orderService.getOrders();
    }

    @RequestMapping(value = "/order/{id}/", method = DELETE)
    public void orderDelete(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }
}
