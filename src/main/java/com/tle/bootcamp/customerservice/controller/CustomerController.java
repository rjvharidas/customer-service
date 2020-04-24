package com.tle.bootcamp.customerservice.controller;

import com.tle.bootcamp.customerservice.domain.Customer;
import com.tle.bootcamp.customerservice.domain.ApiError;
import com.tle.bootcamp.customerservice.domain.Success;
import com.tle.bootcamp.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Success addCustomer(@Valid @RequestBody Customer customer){
        customerRepository.save(customer);
        return new Success("Customer added successfully!!!!");
    }

    @PutMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Object updateCustomer(@Valid @RequestBody Customer customer){
        Optional<Customer> oldCustomer = customerRepository.findById(customer.getId());
        if(oldCustomer.isPresent()){
            update(customer, oldCustomer);
            return new Success("Customer updated successfully!!!!");
        }
        return new ApiError("Bad Request", "Customer Id not found..!!");
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

    @GetMapping(path = "/{customerId}", produces = "application/json")
    public Customer getCustomerById(@PathVariable("customerId") String customerId){
        return customerRepository.findById(customerId).get();
    }

    @DeleteMapping(path = "/{customerId}", produces = "application/json")
    public Object removeCustomerById(@PathVariable("customerId") String customerId){
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            customerRepository.delete(customer.get());
            return new Success("Customer deleted successfully!!!!");
        }
        return new ApiError("Bad Request", "Customer Id not found..!!");
    }

    @ExceptionHandler
    public ApiError processError(Exception e){
        return new ApiError("Internel Server Error", "Something went wrong!!!!!");
    }
}
