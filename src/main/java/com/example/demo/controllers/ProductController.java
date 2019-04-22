package com.example.demo.controllers;

import com.example.demo.models.Customer;
import com.example.demo.models.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping(value="/product/", method=POST)
    public void addProduct(@RequestBody Product product)
    {
        productService.addNewProduct(product);
    }

    @RequestMapping(value="/product/", method=GET)
    public List<Product> allProducts()
    {
        return productService.getProducts();
    }

    @RequestMapping(value = "/product/{id}/",method = DELETE)
    public void productDelete(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }

    @RequestMapping(value="product/{id}",method= PUT)
    public void editProduct(@PathVariable Integer id,@RequestBody Product product){
        productService.editProduct(product,id);
    }

}
