package com.yellow.ordermanageryellow.service;

import com.yellow.ordermanageryellow.Dao.ProductRepository;
import com.yellow.ordermanageryellow.Dto.ProductDTO;
import com.yellow.ordermanageryellow.Dto.ProductNameDTO;
import com.yellow.ordermanageryellow.Exception.ObjectAllReadyExists;
import com.yellow.ordermanageryellow.Dao.RolesRepository;
import com.yellow.ordermanageryellow.exceptions.NoPermissionException;
import com.yellow.ordermanageryellow.exceptions.ObjectAlreadyExistException;
import com.yellow.ordermanageryellow.model.*;
import com.yellow.ordermanageryellow.security.EncryptedData;
import com.yellow.ordermanageryellow.security.JwtToken;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;


@Service
public class ProductService {
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @SneakyThrows
    public Product addProduct(Product product,String token) throws ObjectAllReadyExists {
        String role= this.jwtToken.decryptToken(token, EncryptedData.ROLE);
        String company= this.jwtToken.decryptToken(token, EncryptedData.COMPANY);
        Roles wholeRole = rolesRepository.findById(role).orElse(null);
        if(!wholeRole.getName().equals(RoleName.ADMIN))
            throw new NoPermissionException("You do not have permission to delete product category");
        if (this.productRepository.existsByName(product.getName()))
            throw new ObjectAlreadyExistException("category name already exist");
        Company companyOfUser=new Company();
        companyOfUser.setId(company);
        product.setCompanyId(companyOfUser);
        product.setAuditData(new AuditData(LocalDateTime.now()));
        return this.productRepository.save(product);

    }

    public List<ProductNameDTO> getAllProductsNames(String token, String prefix) {
        List<Product> products = productRepository.findByCompanyIdAndNameAndPrefix(token, prefix).stream().toList();
        List<ProductNameDTO> productList = ProductMapper.INSTANCE.ProductToProductNameDTO(products);
        return productList;
    }

    @SneakyThrows
    public Product editProduct(Product product, String token) throws ObjectAllReadyExists {
        String role= this.jwtToken.decryptToken(token, EncryptedData.ROLE);
        String company= this.jwtToken.decryptToken(token, EncryptedData.COMPANY);
        Product productOptional = this.productRepository.findById(product.getId()).orElse(null);
        if (productOptional==null)
            throw new NoSuchElementException("product doesn't exist");
        String companyOfProduct = productOptional.getCompanyId().getId();
        Roles wholeRole = rolesRepository.findById(role).orElse(null);
        if(!wholeRole.getName().equals(RoleName.ADMIN)|| !company.equals(companyOfProduct))
            throw new NoPermissionException("You do not have permission to update product");
        if (!productOptional.getName().equals(product.getName()) && productRepository.existsByName(product.getName()))
            throw new ObjectAllReadyExists("You need a unique name for product");
        product.getAuditData().setUpdateDate(LocalDateTime.now());
        return productRepository.save(product);
    }
    public void deleteProduct(String id, String token) {

        String role= this.jwtToken.decryptToken(token, EncryptedData.ROLE);
        String company= this.jwtToken.decryptToken(token, EncryptedData.COMPANY);
        Product ProductFromDb = this.productRepository.findById(id).orElse(null);
        if (ProductFromDb == null) {
            throw new NoSuchElementException("category is not found");
        }
        String companyOfCategory = "7";
        //ProductFromDb.getCompanyId().getId();
        Roles wholeRole = rolesRepository.findById(role).orElse(null);
        if( !wholeRole.getName().equals(RoleName.ADMIN)|| !company.equals(companyOfCategory))
            throw new NoPermissionException("You do not have permission to delete product category");
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

