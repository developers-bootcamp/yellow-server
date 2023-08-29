package com.yellow.ordermanageryellow.service;

import com.yellow.ordermanageryellow.Dao.OrdersRepository;
import com.yellow.ordermanageryellow.Dao.ProductRepository;
import com.yellow.ordermanageryellow.Dto.OrderDTO;
import com.yellow.ordermanageryellow.Dto.OrderMapper;
import com.yellow.ordermanageryellow.model.Order_Items;
import com.yellow.ordermanageryellow.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChargingService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    public void chargingStep(Orders order) {
        Orders orderFromMongo=ordersRepository.findById(order.getId()).orElse(null);
        try{
            orderFromMongo.setOrderStatusId(Orders.status.charging);
            for (Order_Items item:orderFromMongo.getOrderItems())
            {
                if(item.getProductId().getInventory()<item.getQuantity()){
                    orderFromMongo.setOrderStatusId(Orders.status.cancelled);
                    ordersRepository.save(orderFromMongo);
                    return;
                }
                 else{
                    item.getProductId().setInventory((int)(item.getProductId().getInventory()-item.getQuantity()));
                    productRepository.save(item.getProductId());
                 }
            }
            OrderDTO orderDTO = OrderMapper.INSTANCE.orderToOrderDTO(orderFromMongo);
            rabbitMQProducer.sendMessage(orderDTO);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public void CompletedPayment(OrderDTO orderDTO) {
        Orders order=ordersRepository.findById(orderDTO.getOrderId()).orElse(null);
        if(order.getOrderStatusId().equals(Orders.status.approved))
            order.setOrderStatusId(Orders.status.packing);
        else {
            order.setOrderStatusId(Orders.status.cancelled);
            for (Order_Items item:order.getOrderItems())
                item.getProductId().setInventory((int)(item.getProductId().getInventory()+item.getQuantity()));
        }
        ordersRepository.save(order);
    }
}
