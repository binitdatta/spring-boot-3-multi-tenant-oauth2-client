package com.rollingstone.config;

import com.rollingstone.model.Customer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.*;

import java.util.List;

@HttpExchange("http://localhost:14040")
public interface CustomerClient {
    @GetExchange("/api/customer-service/customer/all")
    List<Customer> getCustomers();
    @PostExchange("/api/customer-service/customer")
    ResponseEntity<?> saveCustomer(@RequestBody Customer customer);

    @GetExchange("/api/customer-service/customer/{id}")
    Customer getCustomer(@PathVariable("id") long id);

    @PutExchange("/api/customer-service/customer/{id}")
    ResponseEntity<?> updateCustomer(@PathVariable("id") long id, @RequestBody Customer customer);

    @DeleteExchange("/api/customer-service/customer/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable("id") long id);
}
