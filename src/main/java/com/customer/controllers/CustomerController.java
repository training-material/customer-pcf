package com.customer.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.Exception.CustomErrorType;
import com.customer.domain.Customer;
import com.customer.services.CustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/api/customer/")
@Slf4j
public class CustomerController {
	@Autowired
	CustomerService service;

	@PostMapping(value = "/v1/")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
		log.info("Creating Cusomer : {}", customer);

		if (service.isUserExist(customer)) {
			log.error("Unable to create. A Cusomer with name {} already exist", customer.getName());
			return new ResponseEntity(
					new CustomErrorType(
							"Unable to create. A Customer with name " + customer.getName() + " already exist."),
					HttpStatus.CONFLICT);
		}
		service.Save(customer);

		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);

	}

	@GetMapping(value = "/v1/{id}")
	public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
		log.info("Fetching User with id {}", id);
		Customer customer = service.findCustomerByid(id);
		if (customer == null) {
			log.error("Cusomer with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@GetMapping(value = "/v1/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customers = service.getAllCustomers();
		if (customers.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

	}

	@PutMapping(value = "/v1/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
		log.info("updating customer with id is{}", id);
		Customer cust = service.findCustomerByid(id);

		if (cust == null) {
			log.error("Unable to update. Customer with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Customer with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
	
		cust.setAddress(customer.getAddress());
		cust.setPhone(customer.getPhone());
		cust.setName(customer.getPhone());
		service.Save(cust);
		return new ResponseEntity<Customer>(cust, HttpStatus.OK);
	}

	@DeleteMapping(value = "/v1/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable Long id) {
		log.info("Fetching & Deleting customer with id is {}", id);
		Customer customer = service.findCustomerByid(id);
		if (customer == null) {
			log.error("unable to deleting  customer with id is {}", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Customer with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		service.delete(id);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

}