package com.vehicle.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        return builder.routes()

                .route("auth-service", route -> route
                        .path("/api/auth/**")
                        .uri("lb://AUTH-SERVICE"))

                .route("vehicle-service", route -> route
                        .path("/api/vehicles/**")
                        .uri("lb://VEHICLE-SERVICE"))

                .route("customer-service", route -> route
                        .path("/api/customers/**")
                        .uri("lb://CUSTOMER-SERVICE"))

                .route("quotation-service", route -> route
                        .path("/api/quotations/**")
                        .uri("lb://QUOTATION-SERVICE"))

                .route("corporate-service", route -> route
                        .path("/api/corporate/**")
                        .uri("lb://CORPORATE-SERVICE"))

                .build();
    }
}
