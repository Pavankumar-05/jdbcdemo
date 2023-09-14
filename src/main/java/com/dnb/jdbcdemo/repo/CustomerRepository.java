package com.dnb.jdbcdemo.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.dto.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
	public List<Customer> findByCustomerContactNumber(String customerContactNumber);
	
}
