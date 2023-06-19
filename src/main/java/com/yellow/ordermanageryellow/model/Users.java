package com.yellow.ordermanageryellow.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Collation
public class Users {
    @Id
private String id;
private String fullName;
private String password;
@DBRef
private Address address;

private Roles roleId;
private Company companyId;
    @DBRef
private AuditData AuditData;


}
