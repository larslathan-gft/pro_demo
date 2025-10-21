package com.example.demo.repository;

import com.example.demo.model.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CustomerRepositoryTest {

    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        customerRepository = new CustomerRepository();
        customerRepository.init(); // Para cargar el CSV en memoria
    }

    @Test
    public void testFindAll() {
        List<Customer> allCustomers = customerRepository.findAll();
        // Asumimos que en nuestro CSV hay 3 clientes
        Assertions.assertNotNull(allCustomers);
        Assertions.assertEquals(2, allCustomers.size());
    }

    @Test
    public void testFindById_Existing() {
        Customer customer = customerRepository.findById(1L);
        Assertions.assertNotNull(customer);
        Assertions.assertEquals("Juan", customer.getFirstName());
        Assertions.assertEquals("Perez", customer.getLastName());
                Assertions.assertNull(customer.getSsn());
                Assertions.assertEquals("123 Main St", customer.getAddress());
    }

    @Test
    public void testFindById_NotExisting() {
        Customer customer = customerRepository.findById(999L);
        Assertions.assertNull(customer);
    }
}
