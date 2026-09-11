package com.carlease.corporate.service;

import com.carlease.corporate.dto.CorporateRequest;
import com.carlease.corporate.dto.CorporateResponse;
import com.carlease.corporate.entity.CorporateCustomer;
import com.carlease.corporate.entity.CorporateStatus;
import com.carlease.corporate.repository.CorporateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CorporateService {

    private final CorporateRepository corporateRepository;

    public CorporateService(
            CorporateRepository corporateRepository
    ) {
        this.corporateRepository = corporateRepository;
    }

    public CorporateResponse createCorporate(
            CorporateRequest request
    ) {

        if (corporateRepository.existsByCompanyName(
                request.companyName()
        )) {
            throw new IllegalArgumentException(
                    "Corporate customer already exists with company name: "
                            + request.companyName()
            );
        }

        if (corporateRepository.existsByContactEmail(
                request.contactEmail()
        )) {
            throw new IllegalArgumentException(
                    "Corporate customer already exists with email: "
                            + request.contactEmail()
            );
        }

        if (corporateRepository.existsByGstNumber(
                request.gstNumber()
        )) {
            throw new IllegalArgumentException(
                    "Corporate customer already exists with GST number: "
                            + request.gstNumber()
            );
        }

        CorporateCustomer corporate = new CorporateCustomer();

        mapRequestToEntity(request, corporate);

        return mapToResponse(
                corporateRepository.save(corporate)
        );
    }

    public List<CorporateResponse> getAllCorporates() {

        return corporateRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CorporateResponse getCorporateById(Long id) {

        return mapToResponse(findCorporate(id));
    }

    public CorporateResponse getCorporateByEmail(
            String email
    ) {

        CorporateCustomer corporate =
                corporateRepository.findByContactEmail(email)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Corporate customer not found with email: "
                                                + email
                                )
                        );

        return mapToResponse(corporate);
    }

    public List<CorporateResponse> getByStatus(
            CorporateStatus status
    ) {

        return corporateRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CorporateResponse updateCorporate(
            Long id,
            CorporateRequest request
    ) {

        CorporateCustomer corporate = findCorporate(id);

        if (!corporate.getCompanyName()
                .equals(request.companyName())
                && corporateRepository.existsByCompanyName(
                request.companyName())) {

            throw new IllegalArgumentException(
                    "Company name already exists: "
                            + request.companyName()
            );
        }

        if (!corporate.getContactEmail()
                .equals(request.contactEmail())
                && corporateRepository.existsByContactEmail(
                request.contactEmail())) {

            throw new IllegalArgumentException(
                    "Email already exists: "
                            + request.contactEmail()
            );
        }

        if (!corporate.getGstNumber()
                .equals(request.gstNumber())
                && corporateRepository.existsByGstNumber(
                request.gstNumber())) {

            throw new IllegalArgumentException(
                    "GST number already exists: "
                            + request.gstNumber()
            );
        }

        mapRequestToEntity(request, corporate);

        return mapToResponse(
                corporateRepository.save(corporate)
        );
    }

    public CorporateResponse updateStatus(
            Long id,
            CorporateStatus status
    ) {

        CorporateCustomer corporate = findCorporate(id);

        corporate.setStatus(status);

        return mapToResponse(
                corporateRepository.save(corporate)
        );
    }

    public void deleteCorporate(Long id) {

        CorporateCustomer corporate = findCorporate(id);

        corporateRepository.delete(corporate);
    }

    private CorporateCustomer findCorporate(Long id) {

        return corporateRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Corporate customer not found with id: "
                                        + id
                        )
                );
    }

    private void mapRequestToEntity(
            CorporateRequest request,
            CorporateCustomer corporate
    ) {

        corporate.setCompanyName(request.companyName());
        corporate.setContactPerson(request.contactPerson());
        corporate.setContactEmail(request.contactEmail());
        corporate.setContactPhone(request.contactPhone());
        corporate.setGstNumber(request.gstNumber());
        corporate.setAddress(request.address());
        corporate.setCity(request.city());
        corporate.setState(request.state());
        corporate.setPincode(request.pincode());
        corporate.setFleetSize(request.fleetSize());
        corporate.setAnnualTurnover(request.annualTurnover());
    }

    private CorporateResponse mapToResponse(
            CorporateCustomer corporate
    ) {

        return new CorporateResponse(
                corporate.getId(),
                corporate.getCompanyName(),
                corporate.getContactPerson(),
                corporate.getContactEmail(),
                corporate.getContactPhone(),
                corporate.getGstNumber(),
                corporate.getAddress(),
                corporate.getCity(),
                corporate.getState(),
                corporate.getPincode(),
                corporate.getFleetSize(),
                corporate.getAnnualTurnover(),
                corporate.getStatus(),
                corporate.getCreatedAt(),
                corporate.getUpdatedAt()
        );
    }
}
