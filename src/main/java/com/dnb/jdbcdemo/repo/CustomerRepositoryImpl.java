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
import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.utils.DBUtils;


@Repository("customerRepositoryImpl")
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private DBUtils dbUtils;
	
	@Override
	public Customer createCustomer(Customer customer) {
		Optional<Connection> connection = dbUtils.getConnection();
        String createAccountStatement = "Insert into customer"
                + " (customerId,customerName,customerContactNumber,customerAddress,customerPAN,customerUUID)"
                + " values(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;
        if (connection.isPresent()) {
            try {
                preparedStatement = connection.get().prepareStatement(createAccountStatement);
                preparedStatement.setInt(1, customer.getCustomerId());
                preparedStatement.setString(2, customer.getCustomerName());
                preparedStatement.setString(3, customer.getCustomerContactNumber());
                preparedStatement.setString(4, customer.getCustomerAddress());
                preparedStatement.setString(5, customer.getCustomerPAN());
                preparedStatement.setString(6, customer.getCustomerUUID());
                
 

                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    return customer; // should return the actual account object from database.
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (connection.isPresent()) {
                    dbUtils.closeConnection(connection.get());
                }
            }
        } else {
            return null;
        }
        return customer;
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {
		Optional<Connection> connection = dbUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        String query = "select * from account where customerId=?";
        if (connection.isPresent()) {
            try {
                preparedStatement = connection.get().prepareStatement(query);
                preparedStatement.setInt(1, customerId);
                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()) {
                    Customer customer=new Customer();
                    customer.setCustomerId(resultSet.getInt("customerId"));
                    customer.setCustomerName(resultSet.getString("customerName"));
                    customer.setCustomerContactNumber(resultSet.getString("customerContactNumber"));
                    customer.setCustomerAddress(resultSet.getString("customerAddress"));
                    customer.setCustomerPAN(resultSet.getString("customerPAN"));
                    customer.setCustomerUUID(resultSet.getString("customerUUID"));
                    return Optional.ofNullable(customer);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (connection.isPresent()) {
                    dbUtils.closeConnection(connection.get());
                }
            }
        }
        return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		Optional<Connection> connection = dbUtils.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet =null;
        String query = "select * from customer";
        List<Customer>customers=new ArrayList<>();
        if (connection.isPresent()) {
            try {
                preparedStatement = connection.get().prepareStatement(query);
                
                resultSet = preparedStatement.executeQuery();

                if(resultSet.next()) {
                    Customer customer=new Customer();
                    customer.setCustomerId(resultSet.getInt("customerId"));
                    customer.setCustomerName(resultSet.getString("customerName"));
                    customer.setCustomerContactNumber(resultSet.getString("customerContactNumber"));
                    customer.setCustomerAddress(resultSet.getString("customerAddress"));
                    customer.setCustomerPAN(resultSet.getString("customerPAN"));
                    customer.setCustomerUUID(resultSet.getString("customerUUID"));
                    customers.add(customer);
                }
                return customers;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (connection.isPresent()) {
                    dbUtils.closeConnection(connection.get());
                }
            }
        }
        return null;
	}

}
