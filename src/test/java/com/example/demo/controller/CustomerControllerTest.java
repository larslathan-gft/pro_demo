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
    public void testGetCustomers() throws Exception {
        given(customerService.getAllCustomers()).willReturn(Arrays.asList(
                new Customer(1L, "Juan", "Pérez", "juan@example.com", "123456789", "Address", "123-45-6789"),
                new Customer(2L, "Ana", "García", "ana@example.com", "987654321", "Address2", "987-65-4321")
        ));

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                // Validamos que el JSON contenga la lista con 2 elementos
                .andExpect(jsonPath("$.length()").value(2))
                // Revisamos que el primer elemento tenga id = 1 y un fullName
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].fullName").value("Juan Pérez"))
                .andExpect(jsonPath("$[0].ssn").doesNotExist())
                .andExpect(jsonPath("$[1].ssn").doesNotExist());
    }


    @Test
    public void testGetCustomerDetail_NotFound() throws Exception {
        // Si no se encuentra el cliente, devolvemos null
        given(customerService.getCustomerById(anyLong())).willReturn(null);

        mockMvc.perform(get("/customers/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetCustomerDetail_Success() throws Exception {
        Customer customer = new Customer(1L, "Juan", "Pérez", "juan@example.com", "123456789", "Address", "123-45-6789");
        given(customerService.getCustomerById(1L)).willReturn(customer);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Juan"))
                .andExpect(jsonPath("$.lastName").value("Pérez"))
                .andExpect(jsonPath("$.ssn").value("123-45-6789"));
    }
}
