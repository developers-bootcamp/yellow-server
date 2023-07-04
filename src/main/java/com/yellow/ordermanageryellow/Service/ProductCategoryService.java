package com.yellow.ordermanageryellow.Service;
import com.yellow.ordermanageryellow.Dao.ProductCategoryRepository;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService  {
    private final ProductCategoryRepository ProductCategoryRepository;
    @Autowired
    public ProductCategoryService(ProductCategoryRepository ProductCategoryRepository) {
        this.ProductCategoryRepository = ProductCategoryRepository;
    }
    public Optional<ProductCategory> findById(String id){
        try {
           return this.ProductCategoryRepository.findById(id);
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());        }
    }

    public List<ProductCategory> findAll(){
        try {
            return this.ProductCategoryRepository.findAll();
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
    }
    public ProductCategory insert(ProductCategory newCategory) {
        try{
            return this.ProductCategoryRepository.save(newCategory);
        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }
    public Boolean delete(String categoryId) {
        Optional<ProductCategory> optionalCategory;
        try{
            optionalCategory  = this.ProductCategoryRepository.findById(categoryId);
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
        if (optionalCategory.isPresent()) {
            try{
                this.ProductCategoryRepository.deleteById(categoryId);
                return true;
            }
            catch (Exception ex){
                throw new RuntimeException(ex.getMessage());
            }
        } else {
            return false;
        }
    }


    public ProductCategory update(ProductCategory updateCategory){
        try {
            String id= updateCategory.getId();
            Optional<ProductCategory> Category =this.findById(id);
            if (Category.isPresent()) {
                return this.ProductCategoryRepository.save(updateCategory);
            }
            else{
                return null;
            }}
            catch (Exception ex){
                throw new RuntimeException(ex.getMessage());
        }
     }
}