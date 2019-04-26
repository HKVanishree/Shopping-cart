package com.example.demo.services;
import com.example.demo.models.Cart;
import com.example.demo.models.Customer;
import com.example.demo.models.ItemModel;
import com.example.demo.models.Product;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;



@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CartServiceTest {

    @Mock
    CustomerService customerService;

    @Mock
    CartService cartService;

    @Mock
    ProductRepository productRepository;

    @Mock
    CustomerRepository customerRepository;

    @Test
    public void cartCreated()
    {

        Product product=new Product(1,"Bottle",200,1);
        productRepository.save(product);
        Customer customer= new Customer(2,"Ramya","ramya@gmail.com","123");
        customerService.addNewCustomer(customer);
        assertNotEquals(null,cartService.getCart());
        customerRepository.deleteAll();
        productRepository.deleteAll();

    }

    @Test
    public void IdsManuallyInitialized()
    {
        Product product=new Product(3,"Bottle",200,1);
        productRepository.save(product);
        Customer customer= new Customer(4,"Ramya","ramya@gmail.com","123");
        customerService.addNewCustomer(customer);
        assertEquals(3,product.getProductId());
        assertEquals(4,customer.getCustomerId());
        customerRepository.deleteAll();
        productRepository.deleteAll();

    }

    @Test
    public  void  outOfStockItems()
    {
        Product product=new Product(3,"Bottle",200,0);
        productRepository.save(product);
        Customer customer= new Customer(4,"Ramya","ramya@gmail.com","123");
        customerService.addNewCustomer(customer);
        Cart cart=cartService.addToCart(4,3);
        assertEquals(null,cart);
        customerRepository.deleteAll();
        productRepository.deleteAll();

    }

    @Test
    public  void  Instock()
    {
        Product product=new Product(3,"Bottle",200,6);
        productRepository.save(product);
        Customer customer= new Customer(4,"Ramya","ramya@gmail.com","123");
        customerService.addNewCustomer(customer);
        List<ItemModel> itemModelList= new ArrayList<>();
        ItemModel itemModel=new ItemModel();
        itemModelList.add(itemModel);
        Cart cart=new Cart(15,itemModelList,customer);
        assertNotEquals(null,cart);
        customerRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void addItemsToCart()
    {

    }



}