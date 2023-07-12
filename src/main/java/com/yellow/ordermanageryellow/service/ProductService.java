package com.yellow.ordermanageryellow.service;

import com.yellow.ordermanageryellow.dao.ProductRepository;
import com.yellow.ordermanageryellow.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


@Service
public class ProductService implements CommandLineRunner {
    private final ProductRepository ProductRepository;
    @Autowired
    public ProductService(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }
    @Override
    public void run(String... args) {
        Product Product = new Product("12");
        ProductRepository.save(Product);
    }
}