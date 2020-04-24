package com.tle.bootcamp.customerservice.controller;

import com.tle.bootcamp.customerservice.domain.Customer;
import com.tle.bootcamp.customerservice.domain.Success;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerServiceController {

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public Success addCustomer(@Valid @RequestBody Customer customer){
        return new Success("Customer added successfully!!!!");
    }

    @GetMapping(path = "/{customerId}", produces = "application/json")
    public Customer getCustomerById(@PathVariable("customerId") String customerId){
        Customer cus = new Customer("1", "rajeev", "haridas", "Kochi",
                "Kerala", "raj@yahoo.in", "0467882546");
        return cus;
    }
}
