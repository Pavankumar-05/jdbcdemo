package com.dnb.jdbcdemo.dto;

import lombok.Data;

@Data
public class Customer {
	private int customerId;
	private String customerName;
	private String customerContactNumber;
	private String customerAddress;
	private String customerPAN;
	private String customerUUID;
}
