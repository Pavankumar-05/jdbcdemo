package com.dnb.jdbcdemo.dto;

import java.time.LocalDate;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.dnb.jdbcdemo.utils.CustomAccountIdGenerator;
import com.dnb.jdbcdemo.utils.DatePrefixedSequenceIdGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//dialect ->handles the mapping 
@Getter
@EqualsAndHashCode
@NoArgsConstructor
//@ToString(exclude = "customer")
@Data
@Entity //Whenever we will create any ORM mapping things every table or entity must have 1 primary key

public class Account {
	
	

	@Id
	//@NotBlank(message = "Account Id shouldn't be blank!")
	//@GeneratedValue(strategy = GenerationType.AUTO) for int or long
	//@UuidGenerator
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "account_seq")
	@GenericGenerator(name = "account_seq",
					  strategy = "com.dnb.jdbcdemo.utils.CustomAccountIdGenerator",
					  parameters = {
							  @Parameter(name=CustomAccountIdGenerator.INCREMENT_PARAM,value = "50"),
							  @Parameter(name=CustomAccountIdGenerator.VALUE_PREFIX_PARAMETER,value = "A_"),
							  @Parameter(name=CustomAccountIdGenerator.NUMBER_FORMAT_PARAMETER,value = "%05d")
							  }
	)
//	@GenericGenerator(name = "account_seq",
//	  strategy = "com.dnb.jdbcdemo.utils.DatePrefixedSequenceIdGenerator",
//	  parameters = {
//			  @Parameter(name=DatePrefixedSequenceIdGenerator.INCREMENT_PARAM,value = "50")
//			  }
//			)
	private String accountId;
	@Column(nullable = false)
	@NotBlank(message = "Account Holder Name shouldn't be blank!")
	private String accountHolderName;
	
	private String accountType;
	@Min(value = 0,message = "Value should not be negative!")
	private float balance;
	@Length(min = 10,max = 10,message = "Contact Number should be of length 10")
	@Pattern(regexp = "^[0-9]{10}$")
	private String contactNumber;
	private String address;
	private LocalDate accountCreatedDate = LocalDate.now();
	@NotNull(message = "Date must be provided")
	//@Pattern(regexp = "^(\\d{4})-(\\d{2})-(\\d{2})$")
	//@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dob;
	//@Transient //skips the particular field at the tym of creating a table
	private boolean accountStatus = true;
	private int customerId;
	
	
	//private Customer customer;

//	public Account(String accountId, String accountHolderName, String accountType, float balance, String contactNumber,
//			String address, LocalDate accountCreatedDate, LocalDate dob, boolean accountStatus, int customerId) {
//		super();
//		this.accountId = accountId;
//		this.accountHolderName = accountHolderName;
//		this.accountType = accountType;
//		this.balance = balance;
//		this.contactNumber = contactNumber;
//		this.address = address;
//		this.accountCreatedDate = accountCreatedDate;
//		this.dob = dob;
//		this.accountStatus = accountStatus;
//		this.customerId = customerId;
//	}
	

}