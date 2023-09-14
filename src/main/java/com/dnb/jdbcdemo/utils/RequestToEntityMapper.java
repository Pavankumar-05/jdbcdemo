package com.dnb.jdbcdemo.utils;

import org.springframework.stereotype.Component;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.payload.request.AccountRequest;
import com.dnb.jdbcdemo.payload.request.CustomerRequest;

@Component
public class RequestToEntityMapper {
	
	public Account getAccountEntityObject(AccountRequest accountRequest) {
		//Write a method in such a way it need to do transformation for any given types
		Account account =  new Account();
		
		account.setAccountHolderName(accountRequest.getAccountHolderName());
		account.setAccountType(accountRequest.getAccountType());
		account.setAddress(accountRequest.getAddress());
		account.setBalance(accountRequest.getBalance());
		account.setContactNumber(accountRequest.getContactNumber());
		account.setDob(accountRequest.getDob());
		account.setCustomerId(accountRequest.getCustomerId());
		
		return account;
		
		
	}
	
	public Customer getCustomerEntityObject(CustomerRequest customerRequest) {
		
		Customer customer = new Customer();
		
		customer.setCustomerName(customerRequest.getCustomerName());
		customer.setCustomerContactNumber(customerRequest.getCustomerContactNumber());
		customer.setCustomerAddress(customerRequest.getCustomerAddress());
		customer.setCustomerPAN(customerRequest.getCustomerPAN());
		customer.setCustomerUUID(customerRequest.getCustomerUUID());
		
		return customer;
		
	}
	
}
