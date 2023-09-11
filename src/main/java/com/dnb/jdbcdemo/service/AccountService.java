package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import com.dnb.jdbcdemo.dto.Account;

public interface AccountService {
	public Account createAccount(Account account);
	
	public Optional<Account> getAccountById(String accountId);
	
	public List<Account> getAllAccounts();
}
