package com.carlease.quotation.repository;

import com.carlease.quotation.entity.Quotation;
import com.carlease.quotation.entity.QuotationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuotationRepository extends JpaRepository<Quotation, Long> {
    List<Quotation> findByCustomerId(Long customerId);

    List<Quotation> findByStatus(QuotationStatus status);
}
