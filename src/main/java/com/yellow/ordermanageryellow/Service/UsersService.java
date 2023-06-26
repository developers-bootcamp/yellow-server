package com.yellow.ordermanageryellow.Service;
import com.yellow.ordermanageryellow.Dao.UserRepository;
import com.yellow.ordermanageryellow.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements CommandLineRunner {
    private final UserRepository UserRepository;
    @Autowired
    public UsersService(UserRepository UserRepository) {
        this.UserRepository = UserRepository;
    }

    @Override
    public void run(String... args) {
        Users myModel = new Users("12");
        UserRepository.save(myModel);
    }

}