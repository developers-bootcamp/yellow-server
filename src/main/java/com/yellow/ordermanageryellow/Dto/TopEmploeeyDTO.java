package com.yellow.ordermanageryellow.Dto;


import com.yellow.ordermanageryellow.model.Users;

import java.io.Serializable;

public class TopEmploeeyDTO implements Serializable {

    //private Users employee;
    private Long countOfDeliveredOrders;

//    public void setEmployee(Users employee) {
//        this.employee = employee;
//    }

    public void setCountOfDeliveredOrders(Long countOfDeliveredOrders) {
        this.countOfDeliveredOrders = countOfDeliveredOrders;
    }
}
