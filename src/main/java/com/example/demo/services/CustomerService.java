package com.example.demo.services;

import com.example.demo.models.Cart;
import com.example.demo.models.Customer;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CartService cartService;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    public void addNewCustomer(Customer customer) {

      //customer= customerRepository.save(customer);
      Cart cart= new Cart();
      cart.setCartId(customer.getCustomerId());
      cart.setCustomer(customer);
       cart= cartRepository.save(cart);
        customer.setCart(cart);
        customerRepository.save(customer);
    }

    public List<Customer> allCustomer() {
        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll()
                .forEach(customerList::add);
        return customerList;

    }

    public Customer getCustomerById(int id) {
        List<Customer> customerList = new ArrayList<>();
        customerRepository.findAll()
                .forEach(customerList::add);
        for (Customer customer : customerList) {
            if (customer.getCustomerId() == id) {
                return customer;
            }


        }

        return null;

    }

    public void deleteCustomer(int id)
    {
        customerRepository.deleteById(id);
    }

    public void editCustomer(Customer customer,int id){
        Customer customer1=getCustomerById(id);
        customer1.setEmail(customer.getEmail());
        customer1.setName(customer.getName());
        customer1.setPassword(customer.getPassword());
        customerRepository.save(customer1);
    }
}




