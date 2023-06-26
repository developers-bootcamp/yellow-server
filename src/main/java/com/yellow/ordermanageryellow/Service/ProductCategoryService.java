package com.yellow.ordermanageryellow.Service;
import com.yellow.ordermanageryellow.Dao.ProductCategoryRepository;
import com.yellow.ordermanageryellow.model.ProductCategory;
import com.yellow.ordermanageryellow.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import java.util.List;

@Service
public class ProductCategoryService implements CommandLineRunner {
    private final ProductCategoryRepository ProductCategoryRepository;
    @Autowired
    public ProductCategoryService(ProductCategoryRepository ProductCategoryRepository) {
        this.ProductCategoryRepository = ProductCategoryRepository;
    }
    @Override
    public void run(String... args) {
        ProductCategory ProductCategory = new ProductCategory("12");
        ProductCategoryRepository.save(ProductCategory);
    }
    public ResponseEntity<List<ProductCategory>> findAll(){
        List<ProductCategory> categories = this.ProductCategoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);    }
}
