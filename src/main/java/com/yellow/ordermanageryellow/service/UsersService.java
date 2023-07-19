package com.yellow.ordermanageryellow.service;

import com.yellow.ordermanageryellow.DTO.UserDTO;
import com.yellow.ordermanageryellow.DTO.UserMapper;
import com.yellow.ordermanageryellow.dao.UserRepository;
import com.yellow.ordermanageryellow.exception.NotFoundException;
import com.yellow.ordermanageryellow.exception.ObjectExistException;
import com.yellow.ordermanageryellow.exception.WrongPasswordException;
import com.yellow.ordermanageryellow.model.Users;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


@Service
public class UsersService  {
    private final UserRepository UserRepository;
    private final UserMapper userMapper;

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    public UsersService(UserRepository UserRepository, UserMapper userMapper) {
        this.UserRepository = UserRepository;
        this.userMapper = userMapper;
    }


    @SneakyThrows
    public String login(String email, String password) {
        Users user = UserRepository.findUserByEmail(email);
        if (user == null)
            throw new NotFoundException("user not exist");
        else if (!user.getPassword().equals(password))
            throw new WrongPasswordException("invalid password");
        else return generateToken(user);

    }


    @SneakyThrows
    public Users createNewUser(Users newUser) {
        if (!findUser(newUser)) {
            return UserRepository.save(newUser);
        } else
            throw new ObjectExistException("user is already exist");
    }

    public String generateToken(Users user) {
        return user.getCompanyId() + "&" + user.getId() + "&" + user.getRoleId();
    }

    public String[] getToken(String token) {
        String[] tokenS = token.split("&");
        return tokenS;
    }

    public boolean findUser(Users user) {

        Users foundUser = UserRepository.findUserByEmail(user.getAddress().getEmail());
        if (foundUser == null)
            return false;
        return true;

    }

    @SneakyThrows
    public void deleteUser(String id) {
        if (UserRepository.existsById(id))
            UserRepository.deleteById(id);
        else
            throw new NotFoundException("user not found");
    }

    @SneakyThrows
    public Users updateUser(Users user) {
        if (UserRepository.existsById(user.getId()))
            return UserRepository.save(user);
        else
            throw new NotFoundException("user not found");
    }

    @SneakyThrows
    public HashMap<String, String> getCustomerByNames(String prefix) {

        //company and role will be taken from token
        List<Users> users = UserRepository.findByFullNameContainingAndCompanyIdAndRoleId(prefix, "1", "1");
        HashMap<String, String> userMap = new HashMap<>();
        for (Users user : users) {
            userMap.put(user.getId(), user.getFullName());
        }
        return userMap;
    }

    @SneakyThrows
    public List<UserDTO> getUsers(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        //company and role will be taken from token
        Page<Users> users = UserRepository.findAllByCompanyIdAndRoleId("1", "1", pageable);
        return users.map(userMapper::usersToUserDTO).getContent();
    }


}
