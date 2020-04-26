package com.tle.bootcamp.customerservice.service;

import com.tle.bootcamp.customerservice.domain.Customer;

import java.util.List;

public interface CustomerService {
    void addCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer getCustomerById(String id);

    List<Customer> getAllCustomers();

    void removeCustomerById(String id);
}
