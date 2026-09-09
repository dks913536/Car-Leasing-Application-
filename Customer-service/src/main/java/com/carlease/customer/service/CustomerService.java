package com.carlease.customer.service;

import com.carlease.customer.dto.CustomerRequest;
import com.carlease.customer.dto.CustomerResponse;
import com.carlease.customer.dto.EligibilityResponse;
import com.carlease.customer.entity.Customer;
import com.carlease.customer.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerResponse createCustomer(CustomerRequest request) {

        if (customerRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException(
                    "Customer already exists with email: " + request.email()
            );
        }

        if (customerRepository.existsByPhone(request.phone())) {
            throw new IllegalArgumentException(
                    "Customer already exists with phone: " + request.phone()
            );
        }

        Customer customer = new Customer();

        mapRequestToEntity(request, customer);

        return mapToResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public CustomerResponse getCustomerById(Long id) {

        Customer customer = findCustomer(id);

        return mapToResponse(customer);
    }

    public CustomerResponse getCustomerByEmail(String email) {

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Customer not found with email: " + email
                        )
                );

        return mapToResponse(customer);
    }

    public CustomerResponse updateCustomer(
            Long id,
            CustomerRequest request
    ) {

        Customer customer = findCustomer(id);

        if (!customer.getEmail().equals(request.email())
                && customerRepository.existsByEmail(request.email())) {

            throw new IllegalArgumentException(
                    "Customer already exists with email: " + request.email()
            );
        }

        if (!customer.getPhone().equals(request.phone())
                && customerRepository.existsByPhone(request.phone())) {

            throw new IllegalArgumentException(
                    "Customer already exists with phone: " + request.phone()
            );
        }

        mapRequestToEntity(request, customer);

        return mapToResponse(customerRepository.save(customer));
    }

    public void deleteCustomer(Long id) {

        Customer customer = findCustomer(id);

        customerRepository.delete(customer);
    }

    public EligibilityResponse checkEligibility(Long id) {

        Customer customer = findCustomer(id);

        boolean eligible =
                customer.getCreditScore() >= 650
                        && customer.getMonthlyIncome()
                        .compareTo(java.math.BigDecimal.valueOf(25000)) >= 0;

        String reason;

        if (eligible) {
            reason = "Customer meets the minimum income and credit score requirements";
        } else if (customer.getCreditScore() < 650) {
            reason = "Credit score must be at least 650";
        } else {
            reason = "Monthly income must be at least 25000";
        }

        return new EligibilityResponse(
                customer.getId(),
                eligible,
                reason
        );
    }

    private Customer findCustomer(Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Customer not found with id: " + id
                        )
                );
    }

    private void mapRequestToEntity(
            CustomerRequest request,
            Customer customer
    ) {

        customer.setName(request.name());
        customer.setEmail(request.email());
        customer.setPhone(request.phone());
        customer.setAddress(request.address());
        customer.setCity(request.city());
        customer.setState(request.state());
        customer.setPincode(request.pincode());
        customer.setEmploymentType(request.employmentType());
        customer.setMonthlyIncome(request.monthlyIncome());
        customer.setCreditScore(request.creditScore());
    }

    private CustomerResponse mapToResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getAddress(),
                customer.getCity(),
                customer.getState(),
                customer.getPincode(),
                customer.getEmploymentType(),
                customer.getMonthlyIncome(),
                customer.getCreditScore(),
                customer.getCreatedAt(),
                customer.getUpdatedAt()
        );
    }
}
