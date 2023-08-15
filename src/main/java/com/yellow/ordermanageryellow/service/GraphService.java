
package com.yellow.ordermanageryellow.service;
import com.yellow.ordermanageryellow.Dao.OrdersRepository;
import com.yellow.ordermanageryellow.Dto.TopEmploeeyDTO;
import com.yellow.ordermanageryellow.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;


import java.time.LocalDateTime;
import java.util.List;

@Service
public class GraphService {
    @Autowired
    private OrdersRepository orderRepository;
    @Autowired
    private MongoTemplate mongoTemplate;


    public List<TopEmploeeyDTO> topEmployee() {

        Aggregation aggregation = newAggregation(
                match(Criteria.where("auditData.createDate").gte(LocalDateTime.now().minusMonths(3))),
                match(Criteria.where("Statusorder").is(Orders.status.approved)),
                group("employee").count().as("countOfDeliveredOrders"),
                project("countOfDeliveredOrders").and("id").as("employee"),
                sort(Sort.Direction.DESC, "countOfDeliveredOrders"),
                limit(5)
        );

        AggregationResults<TopEmploeeyDTO> result = mongoTemplate.aggregate(
                aggregation, "Order", TopEmploeeyDTO.class
        );

        List<TopEmploeeyDTO> topEmployees = result.getMappedResults();


        return topEmployees;
    }

}

















