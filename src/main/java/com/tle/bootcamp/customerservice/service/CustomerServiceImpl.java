package com.tle.bootcamp.customerservice.service;

import com.tle.bootcamp.customerservice.domain.Customer;
import com.tle.bootcamp.customerservice.exception.CustomerNotFoundException;
import com.tle.bootcamp.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tle.bootcamp.customerservice.constants.ErrorCode.CUSTOMER_NOT_FOUND;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    @CachePut(value = "customer", key = "#customer.id")
    public Customer updateCustomer(Customer customer) {
        Optional<Customer> oldCustomer = customerRepository.findById(customer.getId());
        if (!oldCustomer.isPresent()) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
        return update(customer, oldCustomer);
    }

    @Override
    @Cacheable(value = "customer", key = "#id")
    public Customer getCustomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
        return customer.get();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    @CacheEvict(value = "customer", key = "#id")
    public void removeCustomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
        customerRepository.delete(customer.get());
    }

    public Customer update(Customer customer, Optional<Customer> oldCustomer) {
        Customer updateCustomer = oldCustomer.get();
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        updateCustomer.setCity(customer.getCity());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setPhone(customer.getPhone());
        return customerRepository.save(updateCustomer);
    }
}
