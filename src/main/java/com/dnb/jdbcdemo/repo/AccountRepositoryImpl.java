package com.dnb.jdbcdemo.repo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.naming.InvalidNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dnb.jdbcdemo.dto.Account;
import com.dnb.jdbcdemo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;
import com.dnb.jdbcdemo.utils.DBUtils;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
	@Autowired
	private DBUtils dbUtils;

	@Override
	public Account createAccount(Account account) {
		Optional<Connection> connection = dbUtils.getConnection();
		String createAccountStatement = "insert into account "
				+ "(accountId, accountHolderName, accountType, balance, contactNumber, address, accountCreatedDate, dob, accountStatus, customerId)"
				+ " values(?,?,?,?,?,?,?,?,?,?)";

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
				preparedStatement.setInt(10, account.getCustomer().getCustomerId());

				int result = preparedStatement.executeUpdate(); // execute update returns no of affected rows.
				if (result > 0)
					return account;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (connection.isPresent()) {
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

		ResultSet resultset = null;
		String query = "select * from account where accountId=?";

		if (connection.isPresent()) {

			try {
				preparedStatement = connection.get().prepareStatement(query);
				preparedStatement.setString(1, accountId);

				resultset = preparedStatement.executeQuery();

				if (resultset.next()) {
					Account account = new Account();
					account.setAccountId(resultset.getString("accountId"));

					account.setAccountHolderName(resultset.getString("accountHolderName"));
					account.setAccountType(resultset.getString("accountType"));
					account.setBalance(resultset.getFloat("balance"));
					account.setContactNumber(resultset.getString("contactNumber"));
					account.setAddress(resultset.getString("address"));
					account.setAccountCreatedDate(resultset.getDate("accountCreatedDate").toLocalDate());

					account.setDob(resultset.getDate("dob").toLocalDate());
					account.setAccountStatus(resultset.getBoolean("accountStatus"));

					return Optional.of(account);

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InvalidDateException e) {
				e.printStackTrace();
			} catch (InvalidContactNumberException e) {
				e.printStackTrace();
			} catch (InvalidNameException e) {
				e.printStackTrace();
			} finally {
				if (connection.isPresent()) {
					dbUtils.closeConnection(connection.get());
				}
			}
		}
		return Optional.empty();
	}

	@Override
	public List<Account> getAllAccounts() {

		Optional<Connection> connection = dbUtils.getConnection();
		ResultSet resultSet = null;

		List<Account> accounts = new ArrayList<>();
		String getAll = "select * from account";

		if (connection.isPresent()) {
			try {
				PreparedStatement preparedStatement = connection.get().prepareStatement(getAll);
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					Account account = new Account();
					account.setAccountId(resultSet.getString("accountId"));
					account.setAccountHolderName(resultSet.getString("accountHolderName"));

					account.setAccountType(resultSet.getString("accountType"));
					account.setBalance(resultSet.getFloat("balance"));
					account.setContactNumber(resultSet.getString("contactNumber"));
					account.setAddress(resultSet.getString("address"));
					account.setAddress(resultSet.getString("address"));
					account.setAccountCreatedDate(resultSet.getDate("accountCreatedDate").toLocalDate());
					try {
						account.setDob(resultSet.getDate("dob").toLocalDate());
					} catch (InvalidDateException e) {
						e.printStackTrace();
					}
					account.setAccountStatus(resultSet.getBoolean("accountStatus"));

					accounts.add(account);
				}
				return accounts;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} catch (InvalidContactNumberException e1) {
				e1.printStackTrace();
			} catch (InvalidNameException e1) {
				e1.printStackTrace();
			} catch (InvalidDateException e1) {
				e1.printStackTrace();
			} finally {
				if (connection.isPresent()) {
					dbUtils.closeConnection(connection.get());
				}
			}
		}
		return null;
	}

	@Override
	public boolean deleteAccountById(String accountId) {
		Optional<Connection> connection = dbUtils.getConnection();
		PreparedStatement preparedStatement = null;

		String delete = "DELETE FROM account WHERE accountId=?";
		try {

			preparedStatement = connection.get().prepareStatement(delete);
			preparedStatement.setString(1, accountId);

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (connection.isPresent()) {
				dbUtils.closeConnection(connection.get());
			}
		}
		return false;
	}

}