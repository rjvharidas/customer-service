package com.tle.bootcamp.customerservice.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String exception) {
        super(exception);
    }
}