package com.tle.bootcamp.customerservice.repository;

import com.tle.bootcamp.customerservice.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
