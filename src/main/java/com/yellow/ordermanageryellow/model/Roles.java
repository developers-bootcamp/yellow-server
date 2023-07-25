package com.yellow.ordermanageryellow.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Collation
@NoArgsConstructor
@Data
@Document(collection = "Roles")

public class Roles {
    @Id
    private String id;
    private RoleName name;
    private String desc;
    private AuditData auditData;



}

