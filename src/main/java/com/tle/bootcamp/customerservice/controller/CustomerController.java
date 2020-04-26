package com.tle.bootcamp.customerservice.controller;

import com.tle.bootcamp.customerservice.domain.Customer;
import com.tle.bootcamp.customerservice.domain.Success;
import com.tle.bootcamp.customerservice.service.CustomerService;
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

import java.util.List;

import static com.tle.bootcamp.customerservice.constants.CustomerConstant.CUSTOMER_ADDED;
import static com.tle.bootcamp.customerservice.constants.CustomerConstant.CUSTOMER_DELETED;
import static com.tle.bootcamp.customerservice.constants.CustomerConstant.CUSTOMER_UPDATED;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Success addCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return new Success(CUSTOMER_ADDED);
    }

    @PutMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Success updateCustomer(@Valid @RequestBody Customer customer) {
        customerService.updateCustomer(customer);
        return new Success(CUSTOMER_UPDATED);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    public Customer getCustomerById(@PathVariable("id") String id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping(path = "/", produces = "application/json")
    public List<Customer> getCustomerById() {
        return customerService.getAllCustomers();
    }

    @DeleteMapping(path = "/{id}", produces = "application/json")
    public Success removeCustomerById(@PathVariable("id") String id) {
        customerService.removeCustomerById(id);
        return new Success(CUSTOMER_DELETED);
    }
}
