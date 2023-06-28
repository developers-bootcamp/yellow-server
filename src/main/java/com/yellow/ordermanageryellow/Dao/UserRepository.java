package com.yellow.ordermanageryellow.Dao;
import com.yellow.ordermanageryellow.model.Users;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {

   @Query("{'adress.email' : ?0}")
   Users findUserByEmail(String email);
}