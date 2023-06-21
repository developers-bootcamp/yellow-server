package com.yellow.ordermanageryellow.Service;

import com.yellow.ordermanageryellow.Dao.ProductRepository;
import com.yellow.ordermanageryellow.model.Product;
import com.yellow.ordermanageryellow.model.Users;
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