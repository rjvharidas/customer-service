package com.tle.bootcamp.customerservice.controller;

import com.tle.bootcamp.customerservice.domain.Customer;
import com.tle.bootcamp.customerservice.domain.Success;
import com.tle.bootcamp.customerservice.exception.CustomerNotFoundException;
import com.tle.bootcamp.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static com.tle.bootcamp.customerservice.constants.CustomerConstant.CUSTOMER_ADDED;
import static com.tle.bootcamp.customerservice.constants.CustomerConstant.CUSTOMER_DELETED;
import static com.tle.bootcamp.customerservice.constants.CustomerConstant.CUSTOMER_UPDATED;
import static com.tle.bootcamp.customerservice.constants.ErrorCode.CUSTOMER_NOT_FOUND;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Success addCustomer(@Valid @RequestBody Customer customer) {
        customerRepository.save(customer);
        return new Success(CUSTOMER_ADDED);
    }

    @PutMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Object updateCustomer(@Valid @RequestBody Customer customer) {
        Optional<Customer> oldCustomer = customerRepository.findById(customer.getId());
        if (!oldCustomer.isPresent()) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
        update(customer, oldCustomer);
        return new Success(CUSTOMER_UPDATED);
    }

    @GetMapping(path = "/{customerId}", produces = "application/json")
    public Customer getCustomerById(@PathVariable("customerId") String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
        return customer.get();
    }

    @DeleteMapping(path = "/{customerId}", produces = "application/json")
    public Object removeCustomerById(@PathVariable("customerId") String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException(CUSTOMER_NOT_FOUND);
        }
        customerRepository.delete(customer.get());
        return new Success(CUSTOMER_DELETED);
    }

    private void update(@RequestBody @Valid Customer customer, Optional<Customer> oldCustomer) {
        Customer updateCustomer = oldCustomer.get();
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName(customer.getLastName());
        updateCustomer.setCity(customer.getCity());
        updateCustomer.setEmail(customer.getEmail());
        updateCustomer.setPhone(customer.getPhone());
        customerRepository.save(updateCustomer);
    }
}
