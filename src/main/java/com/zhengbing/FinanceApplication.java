package com.zhengbing;

import com.zhengbing.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FinanceApplication {

	@Autowired
	IUserService userService;

	public static void main(String[] args) {
	    SpringApplication.run(FinanceApplication.class, args);
	}
}
