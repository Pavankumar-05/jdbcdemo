package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.repo.AccountRepository;
@Service
public class AccountServiceImpl implements AccountService {

	//private static AccountService accountService;
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public Account createAccount(Account account) {
		
		return accountRepository.createAccount(account);
	}

	@Override
	public Optional<Account> getAccountById(String accountId) {
		
		return accountRepository.getAccountById(accountId);
	}
	
	
	

	@Override
	public String deleteAccount(String accountId) {
		// TODO Auto-generated method stub
		if(accountRepository.deleteAccountById(accountId)==true) {
			return "Record has been deleted successfully";
		}
		return "Didn't happen";
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.getAllAccounts();
	}

}
