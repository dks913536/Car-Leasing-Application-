package com.carlease.corporate.repository;

import com.carlease.corporate.entity.CorporateCustomer;
import com.carlease.corporate.entity.CorporateStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CorporateRepository extends JpaRepository<CorporateCustomer, Long> {

    Optional<CorporateCustomer> findByCompanyName(String companyName);

    Optional<CorporateCustomer> findByContactEmail(String contactEmail);

    List<CorporateCustomer> findByStatus(CorporateStatus status);

    boolean existsByCompanyName(String companyName);

    boolean existsByContactEmail(String contactEmail);

    boolean existsByGstNumber(String gstNumber);

}
