package com.yellow.ordermanageryellow.model;

import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.DBRef;

enum Discount{
    Percentage,FixedAmount
}
@Collation
public class Product {
    private String id;
    private String name;
    private String desc;
    private double price;
    private Discount discount;
    private Product_Category categoryId;
    private int inventory;
    private Company companyId;
    @DBRef
    private AuditData auditData;
}
