package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;

public interface CustomerService {
	public Customer createCustomer(Customer customer) throws IdNotFoundException;

	public Optional<Customer> getCustomerById(int customerId);

	public boolean deleteCustomerById(int customerId) throws IdNotFoundException;

	public Iterable<Customer> getAllCustomers();
}
