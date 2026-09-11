package com.carlease.quotation.client;

import com.carlease.quotation.dto.VehicleResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class VehicleClient {
    private final RestClient restClient;

    public VehicleClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public VehicleResponse getVehicle(Long vehicleId){
        return restClient.get()
                .uri("http://vehicle-service/api/vehicles/{id}", vehicleId)
                .retrieve()
                .body(VehicleResponse.class);
    }

}
