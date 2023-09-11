package com.dnb.jdbcdemo.dto;

import java.time.LocalDate;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;

import com.dnb.jdbcdemo.exceptions.InvalidContactNumberException;
import com.dnb.jdbcdemo.exceptions.InvalidDateException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

//dialect ->handles the mapping 
@Getter
@EqualsAndHashCode
@NoArgsConstructor
//@ToString(exclude = "customer")

@Entity //Whenever we will create any ORM mapping things every table or entity must have 1 primary key

public class Account {
	@Id
	private String accountId;
	@Column(nullable = false)
	private String accountHolderName;
	private String accountType;
	private float balance;
	private String contactNumber;
	private String address;
	private LocalDate accountCreatedDate = LocalDate.now();
	private LocalDate dob;
	//@Transient //skips the particular field at the tym of creating a table
	private boolean accountStatus = true;
	private int customerId;
	//private Customer customer;

	public Account(String accountId, String accountHolderName, String accountType, float balance, String contactNumber,
			String address, LocalDate accountCreatedDate, LocalDate dob, boolean accountStatus, int customerId)
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
		this.setCustomerId(customerId);
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
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountHolderName=" + accountHolderName + ", accountType="
				+ accountType + ", balance=" + balance + ", contactNumber=" + contactNumber + ", address=" + address
				+ ", accountCreatedDate=" + accountCreatedDate + ", dob=" + dob + ", accountStatus=" + accountStatus
				+ ", customerId=" + customerId + "]";
	}
	

}