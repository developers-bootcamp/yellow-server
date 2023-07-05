package com.yellow.ordermanageryellow.Service;
import Exceptions.ObjectAlreadyExistException;
import com.yellow.ordermanageryellow.Dao.ProductCategoryRepository;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductCategoryService  {
    private final ProductCategoryRepository ProductCategoryRepository;
    @Autowired
    public ProductCategoryService(ProductCategoryRepository ProductCategoryRepository) {
        this.ProductCategoryRepository = ProductCategoryRepository;
    }

    public List<ProductCategory> findAll(){
            return this.ProductCategoryRepository.findAll();
    }
    public ProductCategory insert(String name, String description) {
        if(this.ProductCategoryRepository.existsByName((name))==true)
            throw new ObjectAlreadyExistException("category name already exist");
        ProductCategory newCategory= new ProductCategory();
        newCategory.setName(name);
        newCategory.setDesc(description);
        return this.ProductCategoryRepository.save(newCategory);
    }
        public void delete(String categoryId) {
            this.ProductCategoryRepository.deleteById(categoryId);
        }

    public ProductCategory update(String categoryId, String name, String description){
            ProductCategory Category = ProductCategoryRepository.findById(categoryId).orElse(null);
            ProductCategory updateCategory=new ProductCategory();
            updateCategory.setName(name);
            updateCategory.setDesc(description);
            return this.ProductCategoryRepository.save(updateCategory);
    }
     }
