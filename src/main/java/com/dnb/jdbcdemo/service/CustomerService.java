package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import com.dnb.jdbcdemo.dto.Customer;

public interface CustomerService {
	public Customer createCustomer(Customer customer);

	public Optional<Customer> getCustomerById(int customerId);

	public String deleteCustomerById(int customerId);

	public List<Customer> getAllCustomers();
}
