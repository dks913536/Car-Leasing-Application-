package com.carlease.quotation.service;

import com.carlease.quotation.client.CustomerClient;
import com.carlease.quotation.client.VehicleClient;
import com.carlease.quotation.dto.CustomerResponse;
import com.carlease.quotation.dto.QuotationRequest;
import com.carlease.quotation.dto.QuotationResponse;
import com.carlease.quotation.dto.VehicleResponse;
import com.carlease.quotation.entity.Quotation;
import com.carlease.quotation.entity.QuotationStatus;
import com.carlease.quotation.repository.QuotationRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class QuotationService {

    private final QuotationRepository quotationRepository;
    private final VehicleClient vehicleClient;
    private final CustomerClient customerClient;

    public QuotationService(
            QuotationRepository quotationRepository,
            VehicleClient vehicleClient,
            CustomerClient customerClient
    ) {
        this.quotationRepository = quotationRepository;
        this.vehicleClient = vehicleClient;
        this.customerClient = customerClient;
    }

    public QuotationResponse createQuotation(QuotationRequest request) {

        CustomerResponse customer = customerClient.getCustomer(request.customerId());

        if (customer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        VehicleResponse vehicle = vehicleClient.getVehicle(request.vehicleId());

        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle not found");
        }

        if (!"AVAILABLE".equalsIgnoreCase(vehicle.status())) {
            throw new IllegalArgumentException(
                    "Vehicle is not available for leasing"
            );
        }

        BigDecimal monthlyLeaseAmount = calculateMonthlyLeaseAmount(
                vehicle.pricePerMonth(),
                request.downPayment(),
                request.leaseTenureMonths()
        );

        BigDecimal totalLeaseAmount = monthlyLeaseAmount
                .multiply(BigDecimal.valueOf(request.leaseTenureMonths()))
                .add(request.downPayment());

        Quotation quotation = new Quotation();

        quotation.setCustomerId(customer.id());
        quotation.setVehicleId(vehicle.id());
        quotation.setMonthlyLeaseAmount(monthlyLeaseAmount);
        quotation.setLeaseTenureMonths(request.leaseTenureMonths());
        quotation.setDownPayment(request.downPayment());
        quotation.setTotalLeaseAmount(totalLeaseAmount);
        quotation.setStatus(QuotationStatus.GENERATED);

        return mapToResponse(
                quotationRepository.save(quotation),
                customer,
                vehicle
        );
    }

    public List<QuotationResponse> getAllQuotations() {

        return quotationRepository.findAll()
                .stream()
                .map(this::mapQuotationWithoutExternalData)
                .toList();
    }

    public QuotationResponse getQuotationById(Long id) {

        Quotation quotation = findQuotation(id);

        CustomerResponse customer =
                customerClient.getCustomer(quotation.getCustomerId());

        VehicleResponse vehicle =
                vehicleClient.getVehicle(quotation.getVehicleId());

        return mapToResponse(quotation, customer, vehicle);
    }

    public List<QuotationResponse> getQuotationsByCustomer(Long customerId) {

        customerClient.getCustomer(customerId);

        return quotationRepository.findByCustomerId(customerId)
                .stream()
                .map(quotation -> {

                    VehicleResponse vehicle =
                            vehicleClient.getVehicle(quotation.getVehicleId());

                    CustomerResponse customer =
                            customerClient.getCustomer(quotation.getCustomerId());

                    return mapToResponse(
                            quotation,
                            customer,
                            vehicle
                    );
                })
                .toList();
    }

    public QuotationResponse updateStatus(
            Long id,
            QuotationStatus status
    ) {

        Quotation quotation = findQuotation(id);

        quotation.setStatus(status);

        Quotation saved = quotationRepository.save(quotation);

        CustomerResponse customer =
                customerClient.getCustomer(saved.getCustomerId());

        VehicleResponse vehicle =
                vehicleClient.getVehicle(saved.getVehicleId());

        return mapToResponse(saved, customer, vehicle);
    }

    public void deleteQuotation(Long id) {

        Quotation quotation = findQuotation(id);

        quotationRepository.delete(quotation);
    }

    private BigDecimal calculateMonthlyLeaseAmount(
            BigDecimal vehiclePrice,
            BigDecimal downPayment,
            Integer tenureMonths
    ) {

        BigDecimal remainingAmount =
                vehiclePrice.subtract(downPayment);

        if (remainingAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException(
                    "Down payment cannot exceed monthly vehicle price"
            );
        }

        return remainingAmount.divide(
                BigDecimal.valueOf(tenureMonths),
                2,
                java.math.RoundingMode.HALF_UP
        );
    }

    private Quotation findQuotation(Long id) {

        return quotationRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Quotation not found with id: " + id
                        )
                );
    }

    private QuotationResponse mapToResponse(
            Quotation quotation,
            CustomerResponse customer,
            VehicleResponse vehicle
    ) {

        return new QuotationResponse(
                quotation.getId(),
                quotation.getCustomerId(),
                quotation.getVehicleId(),
                vehicle.brand(),
                vehicle.model(),
                customer.name(),
                quotation.getMonthlyLeaseAmount(),
                quotation.getLeaseTenureMonths(),
                quotation.getDownPayment(),
                quotation.getTotalLeaseAmount(),
                quotation.getStatus(),
                quotation.getCreatedAt(),
                quotation.getUpdatedAt()
        );
    }

    private QuotationResponse mapQuotationWithoutExternalData(
            Quotation quotation
    ) {

        return new QuotationResponse(
                quotation.getId(),
                quotation.getCustomerId(),
                quotation.getVehicleId(),
                null,
                null,
                null,
                quotation.getMonthlyLeaseAmount(),
                quotation.getLeaseTenureMonths(),
                quotation.getDownPayment(),
                quotation.getTotalLeaseAmount(),
                quotation.getStatus(),
                quotation.getCreatedAt(),
                quotation.getUpdatedAt()
        );
    }
}