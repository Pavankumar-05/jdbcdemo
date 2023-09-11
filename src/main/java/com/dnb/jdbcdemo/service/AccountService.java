package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;

public interface AccountService {

	public Account createAccount(Account account) throws IdNotFoundException;

	public Optional<Account> getAccountById(String accountId);

	public boolean deleteAccountById(String accountId) throws IdNotFoundException;

	public List<Account> getAllAccounts();
}
