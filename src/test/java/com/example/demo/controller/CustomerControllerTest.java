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
        // Mockeamos la respuesta del servicio
        given(customerService.getAllCustomers()).willReturn(
                Arrays.asList(
                        new Customer(1L, "Juan", "Perez", "juan@test.com", "555-1234", "123 Main St", "111-22-3333"),
                        new Customer(2L, "Ana", "Garcia", "ana@test.com", "555-5678", "456 Elm St", "444-55-6666")
                )
        );

        mockMvc.perform(get("/customers"))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").isArray())
                        .andExpect(jsonPath("$[0].id").value(1))
                        .andExpect(jsonPath("$[0].firstName").value("Juan"))
                        .andExpect(jsonPath("$[0].lastName").value("Perez"))
                        .andExpect(jsonPath("$[0].email").value("juan@test.com"))
                        .andExpect(jsonPath("$[0].phone").value("555-1234"))
                        .andExpect(jsonPath("$[0].address").value("123 Main St"));
    }

    @Test
    public void testGetCustomerDetail_Found() throws Exception {
        Customer mockCustomer = new Customer(10L, "Carlos", "Lopez", "carlos@test.com", "555-9012", "789 Pine St", "456-78-9012");
        given(customerService.getCustomerById(10L)).willReturn(mockCustomer);

        mockMvc.perform(get("/customers/10"))
                .andExpect(status().isOk())
                // Validamos que en el JSON tengamos el objeto completo
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.firstName").value("Carlos"))
                .andExpect(jsonPath("$.lastName").value("Lopez"))
                .andExpect(jsonPath("$.email").value("carlos@test.com"))
                .andExpect(jsonPath("$.phone").value("555-9012"))
                                .andExpect(jsonPath("$.address").value("789 Pine St"));
    }

    @Test
    public void testGetCustomerDetail_NotFound() throws Exception {
        // Si no se encuentra el cliente, devolvemos null
        given(customerService.getCustomerById(anyLong())).willReturn(null);

        mockMvc.perform(get("/customers/999"))
                .andExpect(status().isNotFound());
    }
}
