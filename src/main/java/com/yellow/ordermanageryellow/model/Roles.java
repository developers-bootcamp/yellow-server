package com.yellow.ordermanageryellow.model;

import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

enum name{ Admin,employee,customer};
@Collation
@Document(collection = "Roles")

public class Roles {
    private String id;
    private name name;
    private String desc;
    @DBRef
    private AuditData auditData;


    public Roles(String id) {
        this.id = id;
    }
}
