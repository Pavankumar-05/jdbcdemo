package com.dnb.jdbcdemo.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
	@Id
	private int customerId;
	private String customerName;
	private String customerContactNumber;
	private String customerAddress;
	private String customerPAN;
	private String customerUUID;
	
	
}
