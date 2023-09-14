package com.dnb.jdbcdemo.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.jdbcdemo.dto.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String>{

	public List<Account> findByContactNumber(String contactNumber);
	
}
