package com.carlease.quotation.client;

import com.carlease.quotation.dto.CustomerResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CustomerClient {

    private final RestClient restClient;

    public CustomerClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public CustomerResponse getCustomer(Long customerId) {

        return restClient.get()
                .uri("http://customer-service/api/customers/{id}", customerId)
                .retrieve()
                .body(CustomerResponse.class);
    }
}
