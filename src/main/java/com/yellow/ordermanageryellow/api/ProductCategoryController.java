package com.yellow.ordermanageryellow.api;
import com.yellow.ordermanageryellow.Service.ProductCategoryService;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @Autowired
    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ProductCategory>> getAllCategories() {
        List<ProductCategory> categories;
        categories= productCategoryService.findAll();
        if(!categories.isEmpty()){
            return new ResponseEntity<>(categories, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<ProductCategory> createCategory(@RequestBody ProductCategory newCategory) {
        ProductCategory createdCategory =productCategoryService.insert(newCategory);
        if(createdCategory!=null)
        return new ResponseEntity<>(createdCategory, HttpStatus.OK);
        else
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{categoryId}")

    public ResponseEntity<Boolean> deleteCategory(@PathVariable String categoryId) {
        Boolean DeleteMassage= productCategoryService.delete(categoryId);
        if(DeleteMassage)
        {
            return new ResponseEntity<Boolean>((true), HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>((false),HttpStatus.NOT_FOUND);

    }

    @PutMapping
    public ResponseEntity<ProductCategory> updateCategory(@RequestBody ProductCategory updateCategory) {
        ProductCategory  updateItem=productCategoryService.update(updateCategory);
        if (updateItem  !=null){
            return new ResponseEntity<>(updateItem,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
