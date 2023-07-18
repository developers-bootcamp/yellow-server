package com.yellow.ordermanageryellow;

import com.yellow.ordermanageryellow.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderManagerYellowApplicationTests {
	@Autowired
 CompanyService companyService;
	@Test
	void contextLoads() {
		companyService.getClass();
	}

}
