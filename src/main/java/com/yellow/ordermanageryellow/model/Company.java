package com.yellow.ordermanageryellow.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Company")
@SuperBuilder(toBuilder = true)

public class Company {
    @Id
    private String id;
    private String name;
    private Currency currency;
    private AuditData auditData;
}
