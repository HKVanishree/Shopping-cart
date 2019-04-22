package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value="/customer/", method=POST)
    public void addCustomer(@RequestBody Customer customer)
      {
        customerService.addNewCustomer(customer);
    }

    @RequestMapping(value="/customer/", method=GET)
    public List<Customer> allCustomers()
    {
        return customerService.allCustomer();
    }

    @RequestMapping(value="/customer/{id}",method = PUT)
    public void editACustomer(@RequestBody Customer customer,@PathVariable Integer id){
        customerService.editCustomer(customer,id);
    }

    @RequestMapping(value="/customer/{id}", method=DELETE)
    public void deleteCustomers(@PathVariable Integer id)
    {
         customerService.deleteCustomer(id);
    }


}
