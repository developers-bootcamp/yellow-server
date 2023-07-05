package com.yellow.ordermanageryellow.api;
import Exceptions.ObjectAlreadyExistException;
import com.yellow.ordermanageryellow.Service.ProductCategoryService;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity getAllCategories() {
        List<ProductCategory> categories;
        try{
            categories= productCategoryService.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{name}/{description}")
    public ResponseEntity createCategory(@PathVariable String name,@PathVariable String description) {
        ProductCategory createdCategory;
        try{
            createdCategory=productCategoryService.insert(name,description);
            return new ResponseEntity<>(createdCategory, HttpStatus.OK);
        }
        catch (ObjectAlreadyExistException ex){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory (@PathVariable String categoryId) {
       try {
           productCategoryService.delete(categoryId);
           return new ResponseEntity(HttpStatus.OK);
       }
        catch(EmptyResultDataAccessException ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
       catch (Exception ex){
           return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }

    @PutMapping("/{categoryId}/{name}/{description}")
    public ResponseEntity updateCategory(@PathVariable String categoryId,@PathVariable String name, @PathVariable String description) {
        try{
            ProductCategory updateItem=productCategoryService.update(categoryId,name,description);
            return new ResponseEntity(updateItem,HttpStatus.OK);
        }
        catch (EmptyResultDataAccessException ex){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        catch (Exception ex){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
