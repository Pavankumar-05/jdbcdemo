package com.dnb.jdbcdemo.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.utils.DBUtils;
@Repository
public class AccountRepositoryImpl implements AccountRepository {

	@Autowired
	private DBUtils dbUtils;
	
	@Override
	public Account createAccount(Account account) {
		Optional<Connection> connection = dbUtils.getConnection();
		String createAccountStatement = "insert into account "
				+ "(accountId, accountHolderName, accountType, balance, contactNumber, address, accountCreatedDate, dob,accountStatus)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		if (connection.isPresent()) {
			try {
				preparedStatement = connection.get().prepareStatement(createAccountStatement);
				preparedStatement.setString(1, account.getAccountId());
				preparedStatement.setString(2, account.getAccountHolderName());
				preparedStatement.setString(3, account.getAccountType());
				preparedStatement.setFloat(4, account.getBalance());
				preparedStatement.setString(5, account.getContactNumber());
				preparedStatement.setString(6, account.getAddress());
				preparedStatement.setDate(7, Date.valueOf(account.getAccountCreatedDate()));
				preparedStatement.setDate(8, Date.valueOf(account.getDob()));
				preparedStatement.setBoolean(9, account.isAccountStatus());
				int result = preparedStatement.executeUpdate(); // DML
				if (result > 0) {
					return account;//it should return the actual account object from DB.
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(connection.isPresent()) {
					dbUtils.closeConnection(connection.get());
				}
				
			}
		} else
			return null;
		return account;
		
	}

	

	@Override
	public Optional<Account> getAccountById(String accountId) {
		Optional<Connection> connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM account WHERE accountId = ?";
		if (connection.isPresent()) {
			try {
				preparedStatement = connection.get().prepareStatement(query);
				preparedStatement.setString(1, accountId);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					Account account = new Account();
					account.setAccountId(resultSet.getString("accountId"));
					account.setAccountHolderName(resultSet.getString("accountHolderName"));
					account.setAccountStatus(resultSet.getBoolean("accountStatus"));
					account.setAddress(resultSet.getString("address"));
					account.setDob(resultSet.getDate("dob").toLocalDate());
					account.setBalance(resultSet.getFloat("balance"));
					account.setContactNumber(resultSet.getString("contactNumber"));
					account.setAccountType(resultSet.getString("accountType"));
					account.setAccountCreatedDate(resultSet.getDate("accountCreatedDate").toLocalDate());
					
					return Optional.of(account);
					
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(connection.isPresent()) {
					dbUtils.closeConnection(connection.get());
				}
				
			}
		} else
			return null;
		return Optional.empty();
	}

	@Override
	public boolean deleteAccountById(String accountId) {
		// TODO Auto-generated method stub
		Optional<Connection> connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		String query = "DELETE FROM account WHERE accountId = ?";
		if(connection.isPresent()) {
			try {
				preparedStatement = connection.get().prepareStatement(query);
				preparedStatement.setString(1, accountId);
				int result = preparedStatement.executeUpdate();
				if(result == 0)
					return false;
				else
					return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(connection.isPresent()) {
					dbUtils.closeConnection(connection.get());
				}
			
		}
		
		
	}
		return false;
	}

	@Override
	public List<Account> getAllAccounts() {
		Optional<Connection> connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String query = "SELECT * FROM account";
		List<Account> accounts = new ArrayList<>();
		if (connection.isPresent()) {
			try {
				preparedStatement = connection.get().prepareStatement(query);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {
					Account account = new Account();
					account.setAccountId(resultSet.getString("accountId"));
					account.setAccountHolderName(resultSet.getString("accountHolderName"));
					account.setAccountStatus(resultSet.getBoolean("accountStatus"));
					account.setAddress(resultSet.getString("address"));
					account.setDob(resultSet.getDate("dob").toLocalDate());
					account.setBalance(resultSet.getFloat("balance"));
					account.setContactNumber(resultSet.getString("contactNumber"));
					account.setAccountType(resultSet.getString("accountType"));
					account.setAccountCreatedDate(resultSet.getDate("accountCreatedDate").toLocalDate());
					accounts.add(account);
					
					
				}
				return accounts;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				if(connection.isPresent()) {
					dbUtils.closeConnection(connection.get());
				}
				
			}
		} else
			return null;
		return null;
		
	}
}
