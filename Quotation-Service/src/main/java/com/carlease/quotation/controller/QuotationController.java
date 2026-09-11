package com.carlease.quotation.controller;

import com.carlease.quotation.dto.QuotationRequest;
import com.carlease.quotation.dto.QuotationResponse;
import com.carlease.quotation.entity.QuotationStatus;
import com.carlease.quotation.service.QuotationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quotations")
public class QuotationController {

    private final QuotationService quotationService;

    public QuotationController(QuotationService quotationService) {
        this.quotationService = quotationService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Quotation Service is running");
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<List<QuotationResponse>> getAllQuotations() {
        return ResponseEntity.ok(
                quotationService.getAllQuotations()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<QuotationResponse> getQuotationById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                quotationService.getQuotationById(id)
        );
    }

    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<List<QuotationResponse>> getByCustomer(
            @PathVariable Long customerId
    ) {
        return ResponseEntity.ok(
                quotationService.getQuotationsByCustomer(customerId)
        );
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER', 'SALES_EXECUTIVE', 'ADMIN', 'CORPORATE_USER')")
    public ResponseEntity<QuotationResponse> createQuotation(
            @Valid @RequestBody QuotationRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(quotationService.createQuotation(request));
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('SALES_EXECUTIVE', 'ADMIN')")
    public ResponseEntity<QuotationResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam QuotationStatus status
    ) {
        return ResponseEntity.ok(
                quotationService.updateStatus(id, status)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteQuotation(
            @PathVariable Long id
    ) {
        quotationService.deleteQuotation(id);

        return ResponseEntity.noContent().build();
    }
}
