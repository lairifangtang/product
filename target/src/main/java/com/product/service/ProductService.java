package com.product.service;

import com.product.model.Products;
import com.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Products> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Products> getProductById(int productid) {
        return productRepository.findById(productid);
    }

    public Products saveProduct(Products product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int productid) {
        productRepository.deleteById(productid);
    }
}

