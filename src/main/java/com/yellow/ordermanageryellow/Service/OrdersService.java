package com.yellow.ordermanageryellow.Service;

import com.yellow.ordermanageryellow.Dao.OrdersRepository;
import com.yellow.ordermanageryellow.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;


@Service
    public class OrdersService  {
        @Autowired
        private final OrdersRepository OrdersRepository;
        @Autowired
        public OrdersService(OrdersRepository OrdersRepository) {
            this.OrdersRepository = OrdersRepository;
        }

    @Value("${pageSize}")
    private int pageSize;

    public List<Orders> getOrders(String token, String userId, String status,int pageNumber) {

         String companyId=token;
          Sort.Order sortOrder = Sort.Order.asc("auditData.updateDate");
        Sort sort = Sort.by(sortOrder);

        Pageable pageable = PageRequest.of(pageNumber, pageSize/* pageSize parameter omitted */,sort);

        Page<Orders> pageOrders= OrdersRepository.findByCompanyId_IdAndOrderStatusIdAndEmployee(companyId,status,userId,pageable);
        return pageOrders.getContent();

    }





}