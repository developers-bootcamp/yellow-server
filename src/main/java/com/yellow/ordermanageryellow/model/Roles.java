package com.yellow.ordermanageryellow.model;

import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.DBRef;

enum name{ Admin,employee,customer};
@Collation
public class Roles {
    private String id;
    private name name;
    private String desc;
    @DBRef
    private AuditData auditData;


}
