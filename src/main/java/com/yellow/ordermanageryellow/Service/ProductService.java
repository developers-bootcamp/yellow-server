package com.yellow.ordermanageryellow.service;


import com.yellow.ordermanageryellow.dao.ProductRepository;
import com.yellow.ordermanageryellow.Dto.ProductDTO;
import com.yellow.ordermanageryellow.Dto.ProductNameDTO;
import com.yellow.ordermanageryellow.Exception.ObjectAllReadyExists;
import com.yellow.ordermanageryellow.model.AuditData;
import com.yellow.ordermanageryellow.model.Product;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @SneakyThrows
    public Product addProduct(Product product) throws ObjectAllReadyExists {
        if (productRepository.existsByName(product.getName()) == true)
            throw new ObjectAllReadyExists("Object Is All Ready Exists");
        product.setAuditData(new AuditData(LocalDateTime.now(), null));
        Product newProduct = productRepository.insert(product);
        return newProduct;

    }

    public List<ProductNameDTO> getAllProductsNames(String token, String prefix) {
        List<Product> products = productRepository.findByCompanyIdAndNameAndPrefix(token, prefix).stream().toList();
        List<ProductNameDTO> productList = ProductMapper.INSTANCE.ProductToProductNameDTO(products);
        return productList;
    }

    @SneakyThrows
    public Product editProduct(Product product) {
        Optional<Product> productOptional = this.productRepository.findById(product.getId());

        if (productOptional.isEmpty())
            throw new NoSuchElementException("product doesn't exist");
        Product updatedProduct = productOptional.orElseThrow(() -> new Exception("company not found"));
        if (!updatedProduct.getName().equals(product.getName()) && productRepository.existsByName(product.getName()))
            throw new ObjectAllReadyExists("You need a unique name for product");
        updatedProduct.getAuditData().setUpdateDate(LocalDateTime.now());
        updatedProduct = productRepository.save(updatedProduct);
        return updatedProduct;
    }


    public void deleteProduct(String id) {
        this.productRepository.deleteById(id);
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll().stream().toList();
        if (products == null)
            throw new NoSuchElementException("no content");
        List<ProductDTO> productDTOs = ProductMapper.INSTANCE.productToDto(products);
        return productDTOs;
    }

}