package com.yellow.ordermanageryellow.service;
import com.yellow.ordermanageryellow.dao.CompanyRepository;
import com.yellow.ordermanageryellow.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


@Service
public class CompanyService implements CommandLineRunner {
    private final CompanyRepository CompanyRepository;
    @Autowired
    public CompanyService(CompanyRepository CompanyRepository) {
        this.CompanyRepository = CompanyRepository;
    }
    @Override
    public void run(String... args) {
        Company Company = new Company("12");
        CompanyRepository.save(Company);
    }
}