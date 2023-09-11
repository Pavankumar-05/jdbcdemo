package com.dnb.jdbcdemo.service;
//
//import java.util.List;
//import java.util.Optional;
//
// 
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
// 
//
//import com.dnb.jdbcdemo.dto.Customer;
//import com.dnb.jdbcdemo.repo.CustomerRepository;
//
// 
//
//@Service("customerServiceImpl")
//public class CustomerServiceImpl implements CustomerService {
//
// 
//package com.dnb.jdbcdemo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.repo.AccountRepository;
import com.dnb.jdbcdemo.repo.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@Override
	public Customer createCustomer(Customer customer) throws IdNotFoundException {
		// TODO Auto-generated method stub
		
			return customerRepository.save(customer);		

		
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId);
	}

	@Override
	public boolean deleteCustomerById(int customerId) throws IdNotFoundException{
		// TODO Auto-generated method stub
		boolean isPresent = customerRepository.existsById(customerId);
		if(isPresent) {
			customerRepository.deleteById(customerId);
			
		}else {
			throw new IdNotFoundException("Id not Found");
		}
		if(customerRepository.existsById(customerId))
			return false;
		else
			return true;
	}

	@Override
	public Iterable<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

}	
	
	

//	@Autowired
//	AccountRepository accountRepository;
//
//	@Autowired
//	CustomerRepository customerRepository;
//
//	@Override
//	public Account createAccount(Account account) throws IdNotFoundException {
//
//		Optional<Customer> optional = customerRepository.getCustomerById(account.getCustomerId());
//		if (optional.isPresent())
//			return accountRepository.createAccount(account);
//		else
//			throw new IdNotFoundException("customer not found");
//	}
//
//	@Override
//	public boolean deleteAccountById(String accountId) {
//		return accountRepository.deleteAccountById(accountId);
//
//	}
//
//	@Override
//	public Optional<Account> getAccountById(String accountId) {
//
//		return accountRepository.getAccountById(accountId);
//	}
//
//	@Override
//	public List<Account> getAllAccounts() {
//		return accountRepository.getAllAccounts();
//	}



//	@Autowired
//	CustomerRepository customerRepositoryImpl;
//
// 
//
//	@Override
//	public Customer createCustomer(Customer customer) {
//		return customerRepositoryImpl.createCustomer(customer);
//	}
//
// 
//
//	@Override
//	public String deleteCustomerById(int customerId) {
//
// 
//
//		if (customerRepositoryImpl.deleteCustomerById(customerId))
//			return "User with customerId: " + customerId + " has been removed from the database";
//		return "Deletion Unsuccessful";
//	}
//
// 
//
//	@Override
//	public Optional<Customer> getCustomerById(int customerId) {
//		return customerRepositoryImpl.getCustomerById(customerId);
//	}
//
// 
//
//	@Override
//	public List<Customer> getAllCustomers() {
//		return customerRepositoryImpl.getAllCustomers();
//	}
//
// 
//
//}