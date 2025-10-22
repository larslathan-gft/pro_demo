package com.example.demo.controller;

import com.example.demo.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.example.demo.service.CustomerService;

import java.util.Arrays;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    public void testGetAllCustomers() throws Exception {
        // Mock the service response
            given(customerService.getAllCustomers()).willReturn(
                Arrays.asList(
                    new Customer(1L, "Juan", "Perez", "juan@test.com", "555-1234", "123 Main St", "111-11-1111"),
                    new Customer(2L, "Ana", "Garcia", "ana@test.com", "555-5678", "456 Elm St", "222-22-2222")
                )
            );

            mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                // Validate JSON contains a list with 2 elements
                .andExpect(jsonPath("$.length()").value(2))
                // Check that the first element has id = 1, fullName, and ssn
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fullName").value("Juan Perez"))
                .andExpect(jsonPath("$[0].ssn").value("111-11-1111"));
    }

    @Test
    public void testGetCustomerDetail_Found() throws Exception {
        Customer mockCustomer = new Customer(10L, "Carlos", "Lopez", "carlos@test.com", "555-9012", "789 Pine St", "333-33-3333");
        given(customerService.getCustomerById(10L)).willReturn(mockCustomer);

        mockMvc.perform(get("/customers/10"))
            .andExpect(status().isOk())
            // Verify complete JSON object
            .andExpect(jsonPath("$.id").value(10))
            .andExpect(jsonPath("$.firstName").value("Carlos"))
            .andExpect(jsonPath("$.lastName").value("Lopez"))
            .andExpect(jsonPath("$.email").value("carlos@test.com"))
            .andExpect(jsonPath("$.phone").value("555-9012"))
            .andExpect(jsonPath("$.ssn").value("333-33-3333"));
    }

    @Test
    public void testGetCustomerDetail_NotFound() throws Exception {
        // Si no se encuentra el cliente, devolvemos null
        given(customerService.getCustomerById(anyLong())).willReturn(null);

        mockMvc.perform(get("/customers/999"))
                .andExpect(status().isNotFound());
    }
}
