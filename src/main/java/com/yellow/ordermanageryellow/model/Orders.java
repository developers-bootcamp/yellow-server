package com.yellow.ordermanageryellow.model;
import lombok.*;
import org.springframework.core.annotation.Order;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Document(collection = "Orders")
public class Orders {
    @Id
    private String id;
    private Users employee;
    private Users customer;
    private double totalAmount;
    @DBRef
    private List<Order_Items> orderItems;
    private String orderStatusId;
    private Company companyId;
    private long creditCardNumber;
    private LocalDate expiryOn;
    private String cvc;
    private Boolean notificationFlag;
    @DBRef
    private AuditData auditData;

    public Orders(String id) {
        this.id = id;
    }
}
