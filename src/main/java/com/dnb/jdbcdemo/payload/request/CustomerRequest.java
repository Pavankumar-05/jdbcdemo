package com.dnb.jdbcdemo.payload.request;

import lombok.Data;

@Data
public class CustomerRequest {
	private String customerName;
	private String customerContactNumber;
	private String customerAddress;
	private String customerPAN;
	private String customerUUID;
}
