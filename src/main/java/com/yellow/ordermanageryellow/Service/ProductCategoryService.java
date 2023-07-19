package com.yellow.ordermanageryellow.service;

import com.yellow.ordermanageryellow.exceptions.ObjectAlreadyExistException;
import com.yellow.ordermanageryellow.dao.ProductCategoryRepository;
import com.yellow.ordermanageryellow.model.AuditData;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public ProductCategoryService(ProductCategoryRepository ProductCategoryRepository) {
        this.productCategoryRepository = ProductCategoryRepository;
    }

    public List<ProductCategory> findAll() {
        return this.productCategoryRepository.findAll();
    }

    public ProductCategory insert(ProductCategory newCategory) throws ObjectAlreadyExistException {
        if (this.productCategoryRepository.existsByname(newCategory.getName()))
            throw new ObjectAlreadyExistException("category name already exist");
        newCategory.setAuditData(new AuditData(LocalDateTime.now()));
        return this.productCategoryRepository.save(newCategory);
    }

    public void delete(String categoryId) {
        this.productCategoryRepository.deleteById(categoryId);
    }

    public ProductCategory update(ProductCategory updatedCategory) {
        ProductCategory Category = this.productCategoryRepository.findById(updatedCategory.getId()).orElse(null);
        if (Category == null) {
            throw new NoSuchElementException("category is not found");
        }

        updatedCategory.setAuditData(new AuditData(Category.getAuditData().getCreateDate(), LocalDateTime.now()));
        return this.productCategoryRepository.save(updatedCategory);
    }
}
