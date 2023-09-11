package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.repo.CustomerRepository;

@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepositoryImpl;

	@Override
	public Customer createCustomer(Customer customer) {
		return customerRepositoryImpl.createCustomer(customer);
	}

	@Override
	public String deleteCustomerById(int customerId) {

		if (customerRepositoryImpl.deleteCustomerById(customerId))
			return "User with customerId: " + customerId + " has been removed from the database";
		return "Deletion Unsuccessful";
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {
		return customerRepositoryImpl.getCustomerById(customerId);
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customerRepositoryImpl.getAllCustomers();
	}

}
