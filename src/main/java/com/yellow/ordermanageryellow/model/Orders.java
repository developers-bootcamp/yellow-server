package com.yellow.ordermanageryellow.model;

import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;
import java.util.List;
@Collation
public class Orders {
    private String id_test2;
    private Users employee;
    private Users customer;
    private double totalAmount;
    @DBRef
    private List<Order_Items> orderItems;
    private String OrderStatusId;
    private Company companyId;
    private long CreditCardNumber;
    private LocalDate expiryOn;
    private String cvc;
    private Boolean notificationFlag;
    @DBRef
    private AuditData auditData;

}
