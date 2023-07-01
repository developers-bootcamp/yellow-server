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
import java.util.Optional;

@Service
public class ProductCategoryService  {
    private final ProductCategoryRepository ProductCategoryRepository;
    @Autowired
    public ProductCategoryService(ProductCategoryRepository ProductCategoryRepository) {
        this.ProductCategoryRepository = ProductCategoryRepository;
    }
    //@Override
    //public void run(String... args) {
       // ProductCategory ProductCategory = new ProductCategory("12");
    //  ProductCategoryRepository.save(ProductCategory);
   // }
    public ResponseEntity<List<ProductCategory>> findAll(){
        List<ProductCategory> categories = this.ProductCategoryRepository.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);    }

    public ResponseEntity<String> insert(ProductCategory newCategory) {
        try{
            ProductCategory savedCategory = this.ProductCategoryRepository.save(newCategory);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<>("savedCategory", HttpStatus.OK);
    }
    public ResponseEntity<String> delete(String categoryId) {
        Optional<ProductCategory> optionalCategory = this.ProductCategoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            this.ProductCategoryRepository.deleteById(categoryId);
            return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }
    }
     public ResponseEntity<ProductCategory> update(ProductCategory updateCategory){

        ProductCategory UpdateCategory = this.ProductCategoryRepository.save(updateCategory);

        if(UpdateCategory!=null){
            return new ResponseEntity<>(updateCategory,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<> (null, HttpStatus.NOT_FOUND);
        }
     }


}