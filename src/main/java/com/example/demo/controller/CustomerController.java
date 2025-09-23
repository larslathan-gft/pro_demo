package com.example.demo.controller;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Endpoint para obtener el listado de clientes.
     * Retornamos sólo información básica: id y nombre completo.
     */
    @GetMapping
    public List<CustomerBasicDTO> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return customers.stream()
                .map(c -> new CustomerBasicDTO(c.getId(), c.getFirstName() + " " + c.getLastName()))
                .collect(Collectors.toList());
    }

    /**
     * Endpoint para obtener el detalle completo de un cliente por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerDetail(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DTO interno para retornar información básica de un cliente.
     */
    static class CustomerBasicDTO {
        private Long id;
        private String fullName;

        public CustomerBasicDTO(Long id, String fullName) {
            this.id = id;
            this.fullName = fullName;
        }

        public Long getId() {
            return id;
        }

        public String getFullName() {
            return fullName;
        }
    }
}
