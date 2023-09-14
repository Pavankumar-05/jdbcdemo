package com.dnb.jdbcdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.jdbcdemo.dto.Customer;
import com.dnb.jdbcdemo.exceptions.IdNotFoundException;
import com.dnb.jdbcdemo.payload.request.CustomerRequest;
import com.dnb.jdbcdemo.service.CustomerService;
import com.dnb.jdbcdemo.utils.RequestToEntityMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired
	RequestToEntityMapper mapper;
	
	@Autowired
	CustomerService customerService;
	
	// insert or create Customer : post: @PostMapping
		@PostMapping("/create") // @RequestMapping + pose Method ==> spring 4.3.x

		public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerRequest customer) {
			
			Customer customer1 = mapper.getCustomerEntityObject(customer);
			
			try {
				Customer customer2 = customerService.createCustomer(customer1);

				return new ResponseEntity(customer2, HttpStatus.CREATED);
			} catch (IdNotFoundException e) {
				// TODO Auto-generated catch block
				return ResponseEntity.badRequest().body(e.getMessage());
			}

		}

		@GetMapping("/{CustomerId}") // It should help us to extract data
		public ResponseEntity<?> getCustomerById(@PathVariable("CustomerId") int CustomerId) throws IdNotFoundException {
			Optional<Customer> optional = customerService.getCustomerById(CustomerId);
			if (optional.isPresent()) {
				return ResponseEntity.ok(optional.get());
			} else {
				throw new IdNotFoundException("Provide proper Id. Since its not present");
			}

		}

		@DeleteMapping("/{CustomerId}")
		public ResponseEntity<?> deleteCustomerById(@PathVariable("CustomerId") int CustomerId) throws IdNotFoundException {
			if (customerService.checkCustomerId(CustomerId)) {
				customerService.deleteCustomerById(CustomerId);
				return ResponseEntity.noContent().build();
			} else
				throw new IdNotFoundException("Provide proper Id. Since its not present");
		}

		@GetMapping("/allCustomers/{customerContactNumber:^[0-9]{10}$}")
		public ResponseEntity<?> getAllCustomersByContactNumber(@PathVariable("customerContactNumber") String contactNumber)
				throws IdNotFoundException {
			List<Customer> list = customerService.getCustomerByContactNumber(contactNumber);
			if (list.size() > 0)
				return ResponseEntity.ok(list);
			else
				throw new IdNotFoundException("Provide proper Contact Number.");
		}

		@GetMapping("/allDetails")
		public ResponseEntity<?> getAllCustomers() throws IdNotFoundException {
			List<Customer> list = customerService.getAllCustomers();
			if (list.size() > 0)
				return ResponseEntity.ok(list);
			else
				throw new IdNotFoundException("No details");
		}
}
