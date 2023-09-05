package com.dnb.jdbcdemo.service;

import java.util.List;
import java.util.Optional;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.repo.AccountRepositoryImpl;

public class AccountServiceImpl implements AccountService {

	private static AccountService accountService;
	private AccountServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Account createAccount(Account account) {
		
		return AccountRepositoryImpl.getInstance().createAccount(account);
	}

	@Override
	public Optional<Account> getAccountById(String accountId) {
		
		return AccountRepositoryImpl.getInstance().getAccountById(accountId);
	}
	
	public static AccountService getInstance() {
		if (accountService == null) {
			synchronized (AccountServiceImpl.class) {
				if (accountService == null)
					accountService = new AccountServiceImpl();
			}
		}
		return accountService;
	}

	

	@Override
	public String deleteAccount(String accountId) {
		// TODO Auto-generated method stub
		if(AccountRepositoryImpl.getInstance().deleteAccountById(accountId)==true) {
			return "REcord has been deleted successfully";
		}
		return "Didn't happen";
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return AccountRepositoryImpl.getInstance().getAllAccounts();
	}

}
