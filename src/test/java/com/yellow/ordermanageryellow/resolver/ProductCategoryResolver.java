package com.yellow.ordermanageryellow.resolver;

import com.yellow.ordermanageryellow.model.AuditData;
import com.yellow.ordermanageryellow.model.Company;
import com.yellow.ordermanageryellow.model.ProductCategory;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.time.LocalDateTime;

public class ProductCategoryResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == ProductCategory.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return ProductCategory.builder().desc("unit testing")
                .desc("unit testing is important")
                .auditData(AuditData.builder().updateDate(LocalDateTime.now()).createDate(LocalDateTime.now()).build())
                .CompanyId(Company.builder().id("X").name("SAP").build())
                .build();
    }
}
