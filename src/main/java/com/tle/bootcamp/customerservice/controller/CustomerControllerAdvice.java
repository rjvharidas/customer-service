package com.tle.bootcamp.customerservice.controller;

import com.tle.bootcamp.customerservice.domain.ApiErrorResponse;
import com.tle.bootcamp.customerservice.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<ApiErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        ApiErrorResponse errorDetails = new ApiErrorResponse(HttpStatus.BAD_REQUEST,
                new Date().toString(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public final ResponseEntity<ApiErrorResponse> handleUserNotFoundException(UsernameNotFoundException ex) {
        ApiErrorResponse errorDetails = new ApiErrorResponse(HttpStatus.UNAUTHORIZED,
                new Date().toString(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
        ApiErrorResponse errorDetails = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                new Date().toString(), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}