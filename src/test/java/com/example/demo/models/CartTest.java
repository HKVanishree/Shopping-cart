package com.example.demo.models;

import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.CartService;
import com.example.demo.services.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {

@Autowired
    CartRepository cartRepository;

@Autowired
    CustomerService customerService;
@Autowired
CartService cartService;

@Autowired
    ProductRepository productRepository;

@Autowired
    CustomerRepository customerRepository;
@Test
public void cartCreated()
{
        Product product=new Product(1,"Bottle",200);
        productRepository.save(product);
        Customer customer= new Customer(2,"Ramya","ramya@gmail.com","123");
        customerService.addNewCustomer(customer);
    assertNotEquals("",cartService.getCart());
    productRepository.delete(product);
    customerRepository.deleteById(2);


}

@Test
    public void prductIdManuallyInitialized()
{
    Product product=new Product(1,"Bottle",200);
    productRepository.save(product);
    Customer customer= new Customer(2,"Ramya","ramya@gmail.com","123");
    customerService.addNewCustomer(customer);
    assertEquals(1,product.getProductId());
    assertEquals(2,customer.getCustomerId());
    productRepository.delete(product);
    customerRepository.delete(customer);

}

}