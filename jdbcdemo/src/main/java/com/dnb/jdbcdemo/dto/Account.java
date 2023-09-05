package com.dnb.jdbcdemo.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Account {
	private String accountId;
	private String accountHolderName;
	private String accountType;
	private float balance;
	private String contactNumber;
	private String address;
	private LocalDate accountCreatedDate = LocalDate.now();
	private LocalDate dob;
	private boolean accountStatus;
}
