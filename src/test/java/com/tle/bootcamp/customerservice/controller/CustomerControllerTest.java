package com.tle.bootcamp.customerservice.controller;

import com.tle.bootcamp.customerservice.CustomerServiceApplicationTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
class CustomerControllerTest extends CustomerServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void addCustomer() throws Exception {
        mockMvc.perform(post("/customer/").content(mockCustomer())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Customer added successfully!!!!"));
    }

    @Test
    void updateCustomer() throws Exception {
        mockMvc.perform(put("/customer/").content(mockUpdateCustomer())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Customer updated successfully!!!!"));
    }


    @Test
    void getCustomerById() throws Exception {
        mockMvc.perform(get("/customer/{id}", "10")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value("10"))
                .andExpect(jsonPath("$.city").value("Bangalore"))
                .andExpect(jsonPath("$.state").value("Karnataka"));
        ;
    }

    @Test
    void removeCustomerById() throws Exception {
        mockMvc.perform(delete("/customer/{id}", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message").value("Customer deleted successfully!!!!"));
    }

    @Test
    void processError() throws Exception {
        mockMvc.perform(get("/customer/{id}", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.description").value("Customer Id not found..!!"));
    }

    private String mockCustomer() {
        return "{\n" +
                "    \"id\": \"10\",\n" +
                "    \"firstName\": \"rajeev\",\n" +
                "    \"lastName\": \"haridas\",\n" +
                "    \"city\": \"Kochi\",\n" +
                "    \"state\": \"Kerala\",\n" +
                "    \"email\": \"raj@yahoo.in\",\n" +
                "    \"phone\": \"0467882546\"\n" +
                "}\n";
    }

    private String mockUpdateCustomer() {
        return "{\n" +
                "    \"id\": \"10\",\n" +
                "    \"firstName\": \"rajeev\",\n" +
                "    \"lastName\": \"haridas\",\n" +
                "    \"city\": \"Bangalore\",\n" +
                "    \"state\": \"Karnataka\",\n" +
                "    \"email\": \"raj@yahoo.in\",\n" +
                "    \"phone\": \"0467882546\"\n" +
                "}\n";
    }
}