package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class CustomerRepositoryTest {

    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        customerRepository = new CustomerRepository();
        customerRepository.init(); // Para cargar el CSV en memoria
    }

    @Test
    void testFindAll() {
        List<Customer> allCustomers = customerRepository.findAll();
        // Asumimos que en nuestro CSV hay 3 clientes
        Assertions.assertNotNull(allCustomers);
        Assertions.assertEquals(3, allCustomers.size());
    }

    @Test
    void testFindById_Existing() {
        Customer customer = customerRepository.findById(1L);
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("Juan", customer.getFirstName());
        Assertions.assertEquals("Pérez", customer.getLastName());
    }

    @Test
    void testFindById_NotExisting() {
        Customer customer = customerRepository.findById(999L);
        Assertions.assertNull(customer);
    }
}
