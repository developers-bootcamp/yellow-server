package com.yellow.ordermanageryellow.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ProductCategory")

public class ProductCategory {
    public ProductCategory(String id) {
        this.Id = id;
    }

    @Id
    private String Id;
    private String Name;
    private String Desc;
    private Company CompanyId;
    @DBRef
    private AuditData AuditData;
}