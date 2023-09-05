package com.dnb.jdbcdemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dnb.jdbcdemo.config.Config;
import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.service.AccountService;

public class JDBCApplication {

	
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
		
//		DBUtils dbUtils = applicationContext.getBean(DBUtils.class);
		
		AccountService accountService = applicationContext.getBean(AccountService.class);
		
//		Optional<Connection> connection = dbUtils.getConnection();
//		System.out.println(connection.get()!=null);
		
		
		Account account = new Account();
		account.setAccountId("1234567890");
		account.setAccountHolderName("Vineeth");
		account.setAccountType("Savings");
		account.setAccountStatus(true);
		account.setAddress("Opposite dnb office Hyderabad");
		account.setBalance(102.4f);
		account.setContactNumber("9987564253");
		account.setAccountCreatedDate(LocalDate.now());
		account.setDob(LocalDate.of(2002, 05, 06));
		
//		System.out.println(AccountServiceImpl.getInstance().createAccount(account));
		
		while(true) {
			//System.out.println("Hello World");
			System.out.println("---------------------------------------------------------");
			System.out.println("1.Create Account\n2.Get Account Details by ID\n3. Delete Account Details by ID\n4. Print all the Records");
			System.out.println();
			System.out.print("Enter your Choice: ");
			int value = sc.nextInt();
			
				
			switch(value) {
			case 1:
				System.out.println("---------------------------------------------------------");
				Account account1 = getInput();
				System.out.println(accountService.createAccount(account1));
				
				break;
			case 2:
				System.out.println("---------------------------------------------------------");
				System.out.println("Enter the account id:");
				String accountNumber = sc.next();
				Optional<Account> returnAccount = accountService.getAccountById(accountNumber);
				System.out.println(returnAccount);
				break;
				
			case 3:
				System.out.println("---------------------------------------------------------");
				System.out.println("Enter the account id:");
				String accountNumber1 = sc.next();
				System.out.println(accountService.deleteAccount(accountNumber1));
				break;
				
			case 4:
				System.out.println("---------------------------------------------------------");
				List<Account> accounts = accountService.getAllAccounts();
				//System.out.println(accountService.getAllAccounts());
				for(int i=0;i<accounts.size();i++)
					System.out.println(accounts.get(i));
				break;
				
			case 5:
				sc.close();
				System.exit(0);

			
				default:
				System.out.println("Please choice a proper option");
			}
		}	
		
		
	}

	private static Account getInput() {
		// Taking input from the user
		//Scanner sc = new Scanner(System.in);
		System.out.println("Enter your Account ID: ");
		String accountId = sc.next();
		System.out.println("Enter your AccountHolder Name: ");
		String accountHolderName = sc.next();
		System.out.println("Enter your AccountType: ");
		String accountType = sc.next();
		System.out.println("Enter your Account Balance: ");
		float balance = sc.nextFloat();
		System.out.println("Enter your AccountHolder Address: ");
		String address = sc.next();
		System.out.println("Enter your AccountHolder ContactNumber: ");
		String contactNumber = sc.next();
		System.out.println("Enter your AccountHolder DOB(yyyy-MM-dd): ");
		String dob = sc.next();
		DateTimeFormatter dateFormatter =  DateTimeFormatter.ofPattern("yyyy-MM-dd");
		//Date date = dateFormatter.format(dob);
		LocalDate date = LocalDate.parse(dob, dateFormatter);
		
		//setting those values
		Account account = new Account();
		account.setAccountId(accountId);
		account.setAccountHolderName(accountHolderName);
		account.setAccountType(accountType);
		account.setAccountStatus(true);
		account.setAddress(address);
		account.setBalance(balance);
		account.setContactNumber(contactNumber);
		account.setAccountCreatedDate(LocalDate.now());
		account.setDob(date);
		
		
		return account;
		
	}

}




//String s = "Vineeth";
//String n = null;
//Optional<String> optional = Optional.ofNullable(s);
//System.out.println(optional.isEmpty());