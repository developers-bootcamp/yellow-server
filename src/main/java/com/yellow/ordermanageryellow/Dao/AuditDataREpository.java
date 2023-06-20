package com.yellow.ordermanageryellow.Dao;

import com.yellow.ordermanageryellow.model.Address;
import com.yellow.ordermanageryellow.model.AuditData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuditDataREpository extends MongoRepository<AuditData, String> {
}
