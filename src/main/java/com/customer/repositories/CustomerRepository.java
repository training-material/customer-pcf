package com.customer.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.customer.domain.Customer;
@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long>{

}
