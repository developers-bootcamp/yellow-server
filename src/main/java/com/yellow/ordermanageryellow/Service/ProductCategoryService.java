package com.yellow.ordermanageryellow.Service;
import Exceptions.ObjectAlreadyExistException;
import com.yellow.ordermanageryellow.Dao.ProductCategoryRepository;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductCategoryService  {
    private final ProductCategoryRepository productCategoryRepository;
    @Autowired
    public ProductCategoryService(ProductCategoryRepository ProductCategoryRepository) {
        this.productCategoryRepository = ProductCategoryRepository;
    }
    public List<ProductCategory> findAll(){
            return this.productCategoryRepository.findAll();
    }
    public ProductCategory insert(ProductCategory newCategory) throws ObjectAlreadyExistException {
        if(this.productCategoryRepository.existsByname(newCategory.getName()))
            throw new ObjectAlreadyExistException("category name already exist");
        return this.productCategoryRepository.save(newCategory);
    }

        public void delete(String categoryId) {
            this.productCategoryRepository.deleteById(categoryId);
        }

    public ProductCategory update(ProductCategory updatedCategory){
            Optional<ProductCategory> Category = this.productCategoryRepository.findById(updatedCategory.get_id());
         if(Category.isEmpty()){
                throw new NoSuchElementException("category is not found");
            }
           return this.productCategoryRepository.save(updatedCategory);
    }
     }
