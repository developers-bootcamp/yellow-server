package com.yellow.ordermanageryellow.controller;

import com.yellow.ordermanageryellow.Exception.ObjectAllReadyExists;
import com.yellow.ordermanageryellow.service.ProductService;
import com.yellow.ordermanageryellow.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product)  {
      Product createdProduct;
        try{
            createdProduct=   productService.addProduct(product);
            return new ResponseEntity<>(createdProduct, HttpStatus.OK);
        }
        catch (ObjectAllReadyExists e){
            return  ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

    @GetMapping("/names/{prefix}")
    public ResponseEntity getAllNamesProducts(@PathVariable("prefix") String prefix, @RequestHeader("token") String token) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProductsNames(token, prefix));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllProduct() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity editProduct(@RequestBody Product product) {
        try {
            Product updatedProduct = this.productService.editProduct(product);
            return ResponseEntity.ok(updatedProduct);
        } catch (NoSuchElementException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") String id) {
        try {
            this.productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
