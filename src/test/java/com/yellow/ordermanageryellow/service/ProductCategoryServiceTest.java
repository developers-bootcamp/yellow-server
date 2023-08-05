package com.yellow.ordermanageryellow.service;

import com.yellow.ordermanageryellow.dao.ProductCategoryRepository;
import com.yellow.ordermanageryellow.dao.RolesRepository;
import com.yellow.ordermanageryellow.exceptions.ObjectAlreadyExistException;
import com.yellow.ordermanageryellow.model.ProductCategory;
import com.yellow.ordermanageryellow.model.RoleNames;
import com.yellow.ordermanageryellow.model.Roles;
import com.yellow.ordermanageryellow.resolver.ProductCategoryResolver;
import com.yellow.ordermanageryellow.security.JwtToken;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;


@ExtendWith({MockitoExtension.class, ProductCategoryResolver.class})
public class ProductCategoryServiceTest {

    @InjectMocks
    private ProductCategoryService productCategoryService;

    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @Mock
    private JwtToken jwtToken;
    @Mock
    private RolesRepository rolesRepository;

    @BeforeEach
    public void beforeEach(ProductCategory productCategory) {
        lenient().when(rolesRepository.findById(any())).thenReturn(Optional.of(Roles.builder().name(RoleNames.ADMIN).build()));
    }

    @Test
    public void insertProductCategory_whenProductCategoryExists_throwException(ProductCategory productCategory) {
        lenient().when(this.productCategoryRepository.existsByname(productCategory.getName())).thenReturn(true);

        assertThatThrownBy(() -> productCategoryService.insert(productCategory, "token")).isInstanceOf(ObjectAlreadyExistException.class);
    }

    @Test
    @SneakyThrows
    public void insertProductCategory_whenProductCategoryNotExists_StoreProductCategory(ProductCategory productCategory) {
        Mockito.when(productCategoryRepository.save(productCategory)).thenReturn(productCategory);

        ProductCategory result = productCategoryService.insert(productCategory,"token");

        Mockito.verify(productCategoryRepository, Mockito.times(1)).save(any(ProductCategory.class));
        Assertions.assertEquals(productCategory, result);
    }

    @Test
    public void updateProductCategory_whenProductCategoryExists_StoreUpdatedProductCategory(ProductCategory productCategory) {
        Mockito.when(this.productCategoryRepository.findById(productCategory.getId())).thenReturn(Optional.of(productCategory));
        Mockito.when(productCategoryRepository.save(productCategory)).thenReturn(productCategory);



        LocalDateTime date = LocalDateTime.now();
        LocalDateTime created = productCategory.getAuditData().getCreateDate();
        productCategory = productCategoryService.update("token", productCategory);

        Mockito.verify(productCategoryRepository, Mockito.times(1)).save(any(ProductCategory.class));
        assertThat(date.isBefore(productCategory.getAuditData().getUpdateDate())).isTrue();
        assertThat(created.isEqual(productCategory.getAuditData().getCreateDate())).isTrue();
    }
}
