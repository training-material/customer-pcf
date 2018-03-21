package com.customer.services;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.domain.Customer;
import com.customer.repositories.CustomerRepository;

@Service
@Slf4j
public class CustomerService {
	@Autowired
	CustomerRepository repository;

	public List<Customer> getAllCustomers() {
		log.info("calling the find all in CustomerRepository");
		return (List<Customer>) repository.findAll();

	}

	public Customer findCustomerByid(Long id) {
		log.info("calling the findCustomerByid in CustomerRepository");
		return repository.findOne(id);
	}

	public Customer Save(Customer customer) {
		log.info("calling the Save in CustomerRepository");
		return repository.save(customer);
	}

	public void delete(Long id) {
		log.info("calling the delete in CustomerRepository");
		repository.delete(id);
	}

	public boolean isUserExist(Customer customer) {
		Customer cust = repository.findOne(customer.getId());
		if (cust == null)
			return false;
		return true;
	}

}
