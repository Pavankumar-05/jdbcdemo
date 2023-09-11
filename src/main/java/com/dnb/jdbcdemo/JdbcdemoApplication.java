package com.dnb.jdbcdemo;


import java.sql.SQLException;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.repo.AccountRepository;
import com.dnb.jdbcdemo.service.AccountService;
import com.dnb.jdbcdemo.service.AccountServiceImpl;

@SpringBootApplication
public class JdbcdemoApplication {
	

	public static void main(String[] args) {
		ApplicationContext applicationContext= SpringApplication.run(JdbcdemoApplication.class, args);
		
		AccountService accountService=applicationContext.getBean(AccountService.class);
		Account account2=new Account();
		account2.setAccountId("ab003");
		account2.setAccountCreatedDate(LocalDate.now());
		account2.setAccountHolderName("Moon");
		account2.setAccountStatus(true);
		account2.setAccountType("Saving");
		account2.setAddress("Hyd");
		account2.setBalance(800009);
		account2.setContactNumber("9807654332");
		account2.setDob(LocalDate.of(2001,10,22));	
		Account acc=accountService.createAccount(account2);
		
		System.out.println(acc);
	}

}
