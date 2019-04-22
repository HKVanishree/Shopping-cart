package com.example.demo.services;
import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll()
                .forEach(productList::add);
        return productList;

    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public void editProduct(Product product,int id){
        Product product1=getProductById(id);
        product1.setPrice(product.getPrice());
        product1.setProductName(product.getProductName());
        productRepository.save(product1);
    }


    public Product getProductById(int id) {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll()
                .forEach(productList::add);
        for (Product product : productList) {
            if (product.getProductId() == id) {
                return product;
            }
        }

        return null;

    }

}
