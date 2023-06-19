package com.yellow.ordermanageryellow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

enum Payment{Credit,debit}
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order_payment {
    @Id
    private Orders ordersId;
    private Users usersId;
    private double amount;
    private int invoiceNumber;

    private Payment payment;
    @DBRef
    private AuditData auditData;
}
