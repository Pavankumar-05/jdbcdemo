package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.repo.AccountRepository;
import com.dnb.jdbcdemo.repo.CustomerRepository;

@Service("accountServiceImpl")
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Account createAccount(Account account) throws IdNotFoundException {

		Optional<Customer> optional = customerRepository.getCustomerById(account.getCustomer().getCustomerId());
		if (optional.isPresent())
			return accountRepository.createAccount(account);
		else
			throw new IdNotFoundException("customer not found");
	}

	@Override
	public boolean deleteAccountById(String accountId) {
		return accountRepository.deleteAccountById(accountId);

	}

	@Override
	public Optional<Account> getAccountById(String accountId) {

		return accountRepository.getAccountById(accountId);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountRepository.getAllAccounts();
	}

}
