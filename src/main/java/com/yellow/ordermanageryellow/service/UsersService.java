package com.yellow.ordermanageryellow.service;
import com.yellow.ordermanageryellow.Dao.CompanyRepository;
import com.yellow.ordermanageryellow.Dao.RolesRepository;
import com.yellow.ordermanageryellow.Dto.UserDTO;
import com.yellow.ordermanageryellow.Dto.UserMapper;
import com.yellow.ordermanageryellow.Dao.RolesRepository;
import com.yellow.ordermanageryellow.Dao.UserRepository;
import com.yellow.ordermanageryellow.exceptions.NotValidStatusExeption;
import com.yellow.ordermanageryellow.exceptions.ObjectAlreadyExistException;
import com.yellow.ordermanageryellow.model.*;
import com.yellow.ordermanageryellow.security.PasswordValidator;
import lombok.SneakyThrows;
import com.yellow.ordermanageryellow.exception.NotFoundException;
import com.yellow.ordermanageryellow.exception.ObjectExistException;
import com.yellow.ordermanageryellow.exception.WrongPasswordException;
import com.yellow.ordermanageryellow.exceptions.NoPermissionException;
import com.yellow.ordermanageryellow.model.ProductCategory;
import com.yellow.ordermanageryellow.model.RoleNames;
import com.yellow.ordermanageryellow.model.Roles;
import com.yellow.ordermanageryellow.model.Users;
import com.yellow.ordermanageryellow.security.EncryptedData;
import com.yellow.ordermanageryellow.security.JwtToken;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class UsersService  {

    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private UserRepository UserRepository;
    @Autowired
    private  UserMapper userMapper;

    @Value("${pageSize}")
    private int pageSize;

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  CompanyRepository companyRepository;



    @SneakyThrows
    public String login(String email, String password) {
        Users user = UserRepository.findByAddressEmail(email);
        if (user == null)
            throw new NotFoundException("user not exist");
        else if (!user.getPassword().equals(password))
            throw new WrongPasswordException("invalid password");
        else return this.jwtToken.generateToken(user);
    }


    @SneakyThrows
    public Users createNewUser(Users newUser,String token) {
        if (!findUser(newUser)) {
            return UserRepository.save(newUser);
        } else
            throw new ObjectExistException("user is already exist");
    }

    public boolean findUser(Users user) {
        Users foundUser = UserRepository.findByAddressEmail(user.getAddress().getEmail());
        if (foundUser == null)
            return false;
        return true;
    }

    @SneakyThrows
    public void deleteUser(String id,String token) {
        String role= this.jwtToken.decryptToken(token, EncryptedData.ROLE);
        String company= this.jwtToken.decryptToken(token, EncryptedData.COMPANY);
        Users userFromDB = this.UserRepository.findById(id).orElse(null);
        if (userFromDB == null) {
            throw new NotFoundException("user is not found");
        }
        String companyOfCategory = userFromDB.getCompanyId().getId();
        Roles wholeRole = rolesRepository.findById(role).orElse(null);
        if(!wholeRole.getName().equals(RoleNames.ADMIN)|| !company.equals(companyOfCategory)){
            throw new NoPermissionException("You do not have permission to update user");
        }
        UserRepository.deleteById(id);
    }

    @SneakyThrows
    public Users updateUser(Users user, String token) throws NoPermissionException {
        String role= this.jwtToken.decryptToken(token, EncryptedData.ROLE);
        String company= this.jwtToken.decryptToken(token, EncryptedData.COMPANY);
        Users userFromDB = this.UserRepository.findById(user.getId()).orElse(null);
        if (userFromDB == null) {
            throw new NotFoundException("user is not found");
        }
        String companyOfCategory = userFromDB.getCompanyId().getId();
        Roles wholeRole = rolesRepository.findById(role).orElse(null);
        if( !wholeRole.getName().equals(RoleNames.ADMIN)|| !company.equals(companyOfCategory)){
            throw new NoPermissionException("You do not have permission to update user");
        }
        return UserRepository.save(user);
    }

    @SneakyThrows
    public HashMap<String, String> getCustomerByNames(String prefix,String token) {
        String roleId= this.jwtToken.decryptToken(token, EncryptedData.ROLE);
        String companyId= this.jwtToken.decryptToken(token, EncryptedData.COMPANY);
        List<Users> users = UserRepository.findByFullNameContainingAndCompanyIdAndRoleId(prefix, companyId, roleId);
        HashMap<String, String> userMap = new HashMap<>();
        for (Users user : users) {
            userMap.put(user.getId(), user.getFullName());
        }
        return userMap;
    }

    @SneakyThrows
    public List<UserDTO> getUsers(int pageNumber,String token) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        String roleId= this.jwtToken.decryptToken(token, EncryptedData.ROLE);
        String companyId= this.jwtToken.decryptToken(token, EncryptedData.COMPANY);
        Page<Users> users = UserRepository.findAllByCompanyIdAndRoleId(companyId, roleId, pageable);
        return users.map(userMapper::usersToUserDTO).getContent();
    }
    @SneakyThrows
    public Users signUp(String fullName,String companyName,String email,String password){

        Users user=new Users();
        user.setFullName(fullName);
        if(PasswordValidator.isValidPassword(password)){
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