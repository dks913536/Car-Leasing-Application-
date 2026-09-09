package com.carlease.customer.controller;

import com.carlease.customer.dto.CustomerRequest;
import com.carlease.customer.dto.CustomerResponse;
import com.carlease.customer.dto.EligibilityResponse;
import com.carlease.customer.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Customer Service is running");
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<CustomerResponse> getCustomerByEmail(
            @PathVariable String email
    ) {
        return ResponseEntity.ok(customerService.getCustomerByEmail(email));
    }

    @GetMapping("/{id}/eligibility")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<EligibilityResponse> checkEligibility(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(customerService.checkEligibility(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<CustomerResponse> createCustomer(
            @Valid @RequestBody CustomerRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.createCustomer(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequest request
    ) {
        return ResponseEntity.ok(
                customerService.updateCustomer(id, request)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long id
    ) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
