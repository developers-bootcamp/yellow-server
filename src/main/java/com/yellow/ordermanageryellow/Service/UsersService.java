package com.yellow.ordermanageryellow.service;
import com.yellow.ordermanageryellow.dao.CompanyRepository;
import com.yellow.ordermanageryellow.dao.RolesRepository;
import com.yellow.ordermanageryellow.DTO.UserDTO;
import com.yellow.ordermanageryellow.DTO.UserMapper;
import com.yellow.ordermanageryellow.dao.UserRepository;
import com.yellow.ordermanageryellow.exceptions.NotValidStatusExeption;
import com.yellow.ordermanageryellow.exceptions.ObjectAlreadyExistException;
import com.yellow.ordermanageryellow.model.*;
import lombok.SneakyThrows;
import com.yellow.ordermanageryellow.exception.NotFoundException;
import com.yellow.ordermanageryellow.exception.ObjectExistException;
import com.yellow.ordermanageryellow.exception.WrongPasswordException;
import com.yellow.ordermanageryellow.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;


@Service
public class UsersService  {
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private  UserMapper userMapper;

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  RolesRepository rolesRepository;
    @Autowired
    private  CompanyRepository companyRepository;



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
    @SneakyThrows
    public Users signUp(String fullName,String companyName,String email,String password){

        Users user=new Users();
        user.setFullName(fullName);
        if(password.equals("")){
            throw new NotValidStatusExeption("password not  valid");
        }
        user.setPassword(password);
        if(!email.contains("@")){
            throw new NotValidStatusExeption("email not valid");
        }
        if (userRepository.existsByAddressEmail(email)) {
            throw new ObjectAlreadyExistException("this user allready exists");
        }
        Address address = new Address();
        user.setAddress(address);
        user.getAddress().setEmail(email);
        user.setRoleId(rolesRepository.getByName(RoleName.ADMIN));
        AuditData auditData = new AuditData();
        auditData.setCreateDate(LocalDateTime.now());
        auditData.setUpdateDate(LocalDateTime.now());
        user.setAuditData(auditData);
        if (companyRepository.existsByName(companyName)){
            throw new ObjectAlreadyExistException("company already exists");
        }
        Company company=new Company();
        company.setName(companyName);
        companyRepository.save(company);
        AuditData auditData1=new AuditData();
        auditData1.setCreateDate(LocalDateTime.now());
        auditData1.setUpdateDate(LocalDateTime.now());
        company.setAuditData(auditData1);
        user.setCompanyId(company);
        userRepository.save(user);
        return user;
    }
}