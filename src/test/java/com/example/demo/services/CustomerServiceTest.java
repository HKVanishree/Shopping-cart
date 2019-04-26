package com.example.demo.services;
import com.example.demo.models.Customer;
import com.example.demo.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @Mock
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCustomers(){

        Customer customer1= new Customer(1,"Ramya","ramya@gmail.com","123");
        Customer customer2= new Customer(2,"Raj","ramya@gmail.com","123");
        Customer customer3= new Customer(3,"Reva","ramya@gmail.com","123");
        Customer customer4= new Customer(4,"Rafeeq","ramya@gmail.com","123");
        List<Customer> customerList=new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        when(customerService.allCustomer()).thenReturn(customerList);
        List<Customer> customers=customerService.allCustomer();
        assertEquals(4,customers.size());
        assertEquals(customerList,customers);
    }

    @Test
    public void wrongCustomerIdReturnsNull(){

        Customer customer1= new Customer(1,"Ramya","ramya@gmail.com","123");
        Customer customer2= new Customer(2,"Raj","ramya@gmail.com","123");
        Customer customer3= new Customer(3,"Reva","ramya@gmail.com","123");
        Customer customer4= new Customer(4,"Rafeeq","ramya@gmail.com","123");
        List<Customer> customerList=new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
        customerList.add(customer3);
        customerList.add(customer4);
        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        when(customerService.getCustomerById(5)).thenReturn(null);
        assertEquals(null,customerService.getCustomerById(5));
    }

//    @Test
//    public void createCustomer()
//    {
//        Customer customer1= new Customer(1,"Ramya","ramya@gmail.com","123");
//        customerRepository.save(customer1);
//        assertEquals(1,customerService.allCustomer().size());

    }

