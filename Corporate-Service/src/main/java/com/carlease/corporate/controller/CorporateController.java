package com.carlease.corporate.controller;

import com.carlease.corporate.dto.CorporateRequest;
import com.carlease.corporate.dto.CorporateResponse;
import com.carlease.corporate.entity.CorporateStatus;
import com.carlease.corporate.service.CorporateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/corporate")
public class CorporateController {

    private final CorporateService corporateService;

    public CorporateController(
            CorporateService corporateService
    ) {
        this.corporateService = corporateService;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok(
                "Corporate Service is running"
        );
    }

    @GetMapping
    @PreAuthorize(
            "hasAnyRole('CORPORATE_USER', 'SALES_EXECUTIVE', 'ADMIN')"
    )
    public ResponseEntity<List<CorporateResponse>> getAllCorporates() {

        return ResponseEntity.ok(
                corporateService.getAllCorporates()
        );
    }

    @GetMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('CORPORATE_USER', 'SALES_EXECUTIVE', 'ADMIN')"
    )
    public ResponseEntity<CorporateResponse> getCorporateById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                corporateService.getCorporateById(id)
        );
    }

    @GetMapping("/email/{email}")
    @PreAuthorize(
            "hasAnyRole('CORPORATE_USER', 'SALES_EXECUTIVE', 'ADMIN')"
    )
    public ResponseEntity<CorporateResponse> getCorporateByEmail(
            @PathVariable String email
    ) {

        return ResponseEntity.ok(
                corporateService.getCorporateByEmail(email)
        );
    }

    @GetMapping("/status/{status}")
    @PreAuthorize(
            "hasAnyRole('CORPORATE_USER', 'SALES_EXECUTIVE', 'ADMIN')"
    )
    public ResponseEntity<List<CorporateResponse>> getByStatus(
            @PathVariable CorporateStatus status
    ) {

        return ResponseEntity.ok(
                corporateService.getByStatus(status)
        );
    }

    @PostMapping
    @PreAuthorize(
            "hasAnyRole('CORPORATE_USER', 'SALES_EXECUTIVE', 'ADMIN')"
    )
    public ResponseEntity<CorporateResponse> createCorporate(
            @Valid @RequestBody CorporateRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        corporateService.createCorporate(request)
                );
    }

    @PutMapping("/{id}")
    @PreAuthorize(
            "hasAnyRole('CORPORATE_USER', 'SALES_EXECUTIVE', 'ADMIN')"
    )
    public ResponseEntity<CorporateResponse> updateCorporate(
            @PathVariable Long id,
            @Valid @RequestBody CorporateRequest request
    ) {

        return ResponseEntity.ok(
                corporateService.updateCorporate(
                        id,
                        request
                )
        );
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize(
            "hasAnyRole('SALES_EXECUTIVE', 'ADMIN')"
    )
    public ResponseEntity<CorporateResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam CorporateStatus status
    ) {

        return ResponseEntity.ok(
                corporateService.updateStatus(
                        id,
                        status
                )
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCorporate(
            @PathVariable Long id
    ) {

        corporateService.deleteCorporate(id);

        return ResponseEntity.noContent().build();
    }
}
