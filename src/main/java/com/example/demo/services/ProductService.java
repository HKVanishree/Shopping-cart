package com.example.demo.services;
import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    static Logger logger = Logger.getLogger(ItemService.class);


    @Autowired
    ProductRepository productRepository;

    public void addNewProduct(Product product) {
        productRepository.save(product);
        logger.info("New product added to inventory");
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        productRepository.findAll()
                .forEach(productList::add);
        logger.info("Fetching list of products");
        return productList;

    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
        logger.warn("Product deleted from inventory");
    }

    public void editProduct(Product product,int id){
        Product product1=getProductById(id);
        product1.setPrice(product.getPrice());
        product1.setProductName(product.getProductName());
        productRepository.save(product1);
        logger.warn("Requested product is updated");
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
