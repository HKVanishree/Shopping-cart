package com.example.demo.controllers;


import com.example.demo.models.Customer;
import com.example.demo.models.ItemModel;
import com.example.demo.services.CartService;
import com.example.demo.services.CustomerService;
import com.example.demo.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/item/", method = POST)
    public void addItem(@RequestBody ItemModel item) {
        item = itemService.addNewItem(item);

    }

    @RequestMapping(value = "/item/", method = GET)
    public List<ItemModel> getItemModels()
    {
        return itemService.getItems();
    }


    @RequestMapping(value = "/item/{id}/",method = DELETE)
    public void itemDelete(@PathVariable Integer id) {
        itemService.deleteItem(id);
    }





}
