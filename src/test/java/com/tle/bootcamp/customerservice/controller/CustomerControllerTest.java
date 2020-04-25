package com.tle.bootcamp.customerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tle.bootcamp.customerservice.CustomerServiceApplicationTests;
import com.tle.bootcamp.customerservice.domain.Customer;
import com.tle.bootcamp.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class CustomerControllerTest extends CustomerServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    @WithMockUser(username = "test", password = "test")
    public void addCustomer() throws Exception {
        mockMvc.perform(post("/customer/").content(asJsonString(mockCustomer()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Customer added successfully!!!!"));
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    void updateCustomer() throws Exception {
        Mockito.when(customerRepository.findById("10")).thenReturn(Optional.of(mockCustomer()));
        mockMvc.perform(put("/customer/").content(asJsonString(mockUpdateCustomer()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Customer updated successfully!!!!"));
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    void getCustomerById() throws Exception {
        Mockito.when(customerRepository.findById("10")).thenReturn(Optional.of(mockUpdateCustomer()));
        mockMvc.perform(get("/customer/{id}", "10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("10"))
                .andExpect(jsonPath("$.city").value("Bangalore"))
                .andExpect(jsonPath("$.state").value("Karnataka"));
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    void removeCustomerById() throws Exception {
        Mockito.when(customerRepository.findById("10")).thenReturn(Optional.of(mockUpdateCustomer()));
        mockMvc.perform(delete("/customer/{id}", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Customer deleted successfully!!!!"));
    }

    @Test
    @WithMockUser(username = "test", password = "test")
    void processError() throws Exception {
        Mockito.when(customerRepository.findById("10")).thenReturn(Optional.empty());
        mockMvc.perform(get("/customer/{id}", "10"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Customer Id not found..!!"));
    }

    private Customer mockCustomer() {
        return new Customer("10", "rajeev", "haridas", "kochi", "Kerala",
                "raj@yahoo.in", "0467882546");
    }

    private Customer mockUpdateCustomer() {
        return new Customer("10", "rajeev", "haridas", "Bangalore", "Karnataka",
                "raj@yahoo.in", "0467882546");
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}