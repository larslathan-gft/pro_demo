package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository {

    private final List<Customer> customers = new ArrayList<>();

    @PostConstruct
    public void init() {
        loadCustomersFromCsv();
    }

    /**
     * Lee el archivo CSV `customers.csv` de la carpeta resources/data y carga los registros en la lista.
     */
    private void loadCustomersFromCsv() {
        try {
            InputStreamReader isr = new InputStreamReader(getClass().getResourceAsStream("/data/customers.csv"));
            CSVReader csvReader = new CSVReader(isr);

            long lineNumber = csvReader.getLinesRead()

            // Leemos todas las líneas
            List<String[]> rows = csvReader.readAll();

            // La primera fila se asume como cabecera: id, firstName, lastName, email, phone
            // Recorremos desde la fila 2
            for (int i = 1; i < rows.size(); i++) {
                String[] row = rows.get(i);
                if (row.length >= 6) {
                    Long id = Long.parseLong(row[0]);
                    String firstName = row[1];
                    String lastName = row[2];
                    String email = row[3];
                    String phone = row[4];
                    String address = row[5];
                    String ssn = row[6];
                    Customer customer = createCustomerWithValidation(id, firstName, lastName, email, phone, address, ssn, "ACTIVE", true, "DEFAULT", System.currentTimeMillis(), "CSV_IMPORT", false, 0.0, "N/A");
                    customers.add(customer);
                }
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a customer with extensive validation - CODE SMELL: Long Parameter List
     */
    private Customer createCustomerWithValidation(Long id, String firstName, String lastName, 
                                                String email, String phone, String address, String ssn,
                                                String status, boolean isActive, String category, 
                                                long timestamp, String source, boolean isVerified,
                                                double creditScore, String notes) {
        // Lots of unnecessary parameters making this method hard to use and maintain
        return new Customer(id, firstName, lastName, email, phone, address, ssn);
    }

    public List<Customer> findAll() {
        return new ArrayList<>(customers);
    }

    public Customer findById(Long id) {
        return customers.stream()
                .filter(c -> c.getId() == (id))
                .findFirst()
                .orElse(null);
    }
}