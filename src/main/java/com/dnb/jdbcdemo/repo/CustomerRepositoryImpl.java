package com.dnb.jdbcdemo.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.utils.DBUtils;

@Repository("customerRepositoryImpl")
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private DBUtils dbUtils;

	@Override
	public Customer createCustomer(Customer customer) {

		Optional<Connection> connection = dbUtils.getConnection();
		String createCustomerStatement = "insert into customer " + "(customerId, customerName,"
				+ "customerContactNumber, customerAddress, customerPAN, customerUUID) values (?,?,?,?,?,?)";

		PreparedStatement prepareStatement = null;

		if (connection.isPresent()) {
			try {
				prepareStatement = connection.get().prepareStatement(createCustomerStatement);
				prepareStatement.setInt(1, customer.getCustomerId());
				prepareStatement.setString(2, customer.getCustomerName());
				prepareStatement.setString(3, customer.getCustomerContactNumber());
				prepareStatement.setString(4, customer.getCustomerAddress());
				prepareStatement.setString(5, customer.getCustomerPAN());
				prepareStatement.setString(6, customer.getCustomerUUID());
				int result = prepareStatement.executeUpdate();

				if (result > 0)
					return customer;
			}

			catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (connection.isPresent())
					dbUtils.closeConnection(connection.get());
			}
		} else
			return null;

		return customer;
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {
		Optional<Connection> connection = dbUtils.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;

		String query = "select * from customer where customerId = ?";

		if (connection.isPresent()) {
			try {
				prepareStatement = connection.get().prepareStatement(query);
				prepareStatement.setInt(1, customerId);
				resultSet = prepareStatement.executeQuery();

				if (resultSet.next()) {
					Customer customer = new Customer();
					customer.setCustomerId(resultSet.getInt("customerId"));
					customer.setCustomerName(resultSet.getString("customerName"));
					customer.setCustomerAddress(resultSet.getString("customerAddress"));
					customer.setCustomerContactNumber(resultSet.getString("customerContactNumber"));
					customer.setCustomerPAN(resultSet.getString("customerPAN"));
					customer.setCustomerUUID(resultSet.getString("customerUUID"));
					return Optional.of(customer);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbUtils.closeConnection(connection.get());
			}
		} else
			return null;

		return Optional.empty();
	}

	public boolean deleteCustomerById(int customerId) {
		Optional<Connection> connection = dbUtils.getConnection();

		PreparedStatement prepareStatement = null;

		String query = "DELETE FROM customer WHERE customerId = ?";

		try {
			prepareStatement = connection.get().prepareStatement(query);
			prepareStatement.setInt(1, customerId);
			int result = prepareStatement.executeUpdate();
			if (result > 0)
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Customer> getAllCustomers() {

		List<Customer> customerList = new ArrayList<>();

		Optional<Connection> connection = dbUtils.getConnection();

		PreparedStatement prepareStatement = null;

		String query = "SELECT * FROM customer";

		try {
			prepareStatement = connection.get().prepareStatement(query);
			ResultSet resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(resultSet.getInt("customerId"));
				customer.setCustomerName(resultSet.getString("customerName"));
				customer.setCustomerAddress(resultSet.getString("customerAddress"));
				customer.setCustomerContactNumber(resultSet.getString("customerContactNumber"));
				customer.setCustomerPAN(resultSet.getString("customerPAN"));
				customer.setCustomerUUID(resultSet.getString("customerUUID"));

				customerList.add(customer);
			}
			return customerList;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
