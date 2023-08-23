package com.yellow.ordermanageryellow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.annotation.Collation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

;


@Data
@Document(collection = "Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id
    private String id;
    private Users employee;
    private Users customer;
    private double totalAmount;
    private List<Order_Items> orderItems;
    private status orderStatusId;
    private Company companyId;
    private long creditCardNumber;
    private String expiryOn;
    private String cvc;
    private Boolean notificationFlag;
    private AuditData auditData;

    public Orders(String id) {
        this.id = id;
    }

    public enum status {New, cancelled, approved, charging, packing, delivered}
}
