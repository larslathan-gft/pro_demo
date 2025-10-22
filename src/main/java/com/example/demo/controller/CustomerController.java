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
    * Endpoint para obtener el detalle completo de un cliente por su ID, incluyendo SSN.
    */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerDetail(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getFirstName(), customer.getLastName(), customer.getEmail(), customer.getPhone(), customer.getAddress(), customer.getSsn());
            return ResponseEntity.ok(customerDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
    * DTO para retornar información completa de un cliente, incluyendo SSN.
    */
    static class CustomerDTO {
        private Long id;
        private String firstName;
        private String lastName;
        private String email;
        private String phone;
        private String address;
        private String ssn;

        public CustomerDTO(Long id, String firstName, String lastName, String email, String phone, String address, String ssn) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.phone = phone;
            this.address = address;
            this.ssn = ssn;
        }

        public Long getId() {
            return id;
        }
        public String getFirstName() {
            return firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public String getEmail() {
            return email;
        }
        public String getPhone() {
            return phone;
        }
        public String getAddress() {
            return address;
        }
        public String getSsn() {
            return ssn;
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
