//package com.yellow.ordermanageryellow.service;
//
//import com.yellow.ordermanageryellow.Dto.OrderDTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Service
//public class RabbitMQConsumer {
//    private static  final Logger LOGGER= LoggerFactory.getLogger(RabbitMQConsumer.class);
//    private final ObjectMapper objectMapper = new ObjectMapper();
//    private final ChargingService chargingService=new ChargingService();
//    @RabbitListener(queues = {"$rabbitmq.queue.consumer.name"})
//    public void receiveMessage(byte[] messageBytes) {
//        try {
//            String messageJson = new String(messageBytes);
//            OrderDTO message = objectMapper.readValue(messageJson, OrderDTO.class);
//            chargingService.CompletedPayment(message);
//            LOGGER.info(String.format("message received: -> %s",message));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}