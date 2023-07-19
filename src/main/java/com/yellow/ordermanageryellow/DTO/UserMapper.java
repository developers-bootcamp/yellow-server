package com.yellow.ordermanageryellow.DTO;

import com.yellow.ordermanageryellow.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
 UserMapper INSTANCE= Mappers.getMapper(UserMapper.class);

 @Mapping(source = "address.email", target = "email")
 @Mapping(source = "address.telephone", target = "telephone")
 @Mapping(source = "address.address", target = "address")
 UserDTO usersToUserDTO(Users entity);


}
