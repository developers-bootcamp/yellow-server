package com.yellow.ordermanageryellow.service;


import com.yellow.ordermanageryellow.model.Roles;
import com.yellow.ordermanageryellow.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

        import com.yellow.ordermanageryellow.dao.RolesRepository;
        import com.yellow.ordermanageryellow.model.Roles;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.CommandLineRunner;
        import org.springframework.stereotype.Service;

@Service
public class RolesService  {
    private final RolesRepository RolesRepository;
    @Autowired
    public RolesService(RolesRepository RolesRepository) {
        this.RolesRepository = RolesRepository;
    }

}