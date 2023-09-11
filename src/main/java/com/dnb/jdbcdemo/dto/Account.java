package com.dnb.jdbcdemo.dto;

import java.time.LocalDate;
import java.util.regex.Pattern;
import javax.naming.InvalidNameException;
import com.dnb.jdbcdemo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@ToString(exclude = "customer")
public class Account {
	private String accountId;
	private String accountHolderName;
	private String accountType;
	private float balance;
	private String contactNumber;
	private String address;
	private LocalDate accountCreatedDate = LocalDate.now();
	private LocalDate dob;
	private boolean accountStatus = true;
	private Customer customer;

	public Account(String accountId, String accountHolderName, String accountType, float balance, String contactNumber,
			String address, LocalDate accountCreatedDate, LocalDate dob, boolean accountStatus, Customer customer)
			throws InvalidNameException, InvalidDateException, InvalidContactNumberException {
		super();
		this.setAccountId(accountId);
		this.setAccountHolderName(accountHolderName);
		this.setAccountCreatedDate(accountCreatedDate);
		this.setBalance(balance);
		this.setAccountStatus(accountStatus);
		this.setContactNumber(contactNumber);
		this.setDob(dob);
		this.setAccountType(accountType);
		this.setCustomer(customer);
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public void setAccountHolderName(String accountHolderName) throws InvalidNameException {
		// length should be min 2 character, no chars no numbers.
		if (Pattern.compile("^[a-zA-Z]{2,}$").matcher(accountHolderName).find())
			this.accountHolderName = accountHolderName;
		else {
			throw new InvalidNameException("Name is invalid");
		}
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public void setContactNumber(String contactNumber) throws InvalidContactNumberException {
		if (Pattern.compile("^[0-9]{10}$").matcher(contactNumber).find())
			this.contactNumber = contactNumber;
		else {
			throw new InvalidContactNumberException("Contact number not valid");
		}
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAccountCreatedDate(LocalDate accountCreatedDate) throws InvalidDateException {
		if (Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$").matcher(accountCreatedDate.toString())
				.find())
			this.accountCreatedDate = accountCreatedDate;
		else {
			throw new InvalidDateException("Date is invalid");
		}
	}

	public void setDob(LocalDate dob) throws InvalidDateException {
		if (Pattern.compile("^\\d{4}-\\d{2}-\\d{2}").matcher(dob.toString()).find())
			this.dob = dob;
		else {
			throw new InvalidDateException("Date is invalid");
		}
	}
	public void setAccountStatus(boolean accountStatus) {
		this.accountStatus = accountStatus;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}