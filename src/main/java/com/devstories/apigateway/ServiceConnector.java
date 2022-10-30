package com.devstories.apigateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ServiceConnector {

    @Value("${authService.uri}")
    private String authServiceUri;

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(authService -> authService
                        .path("/auth-service/**")
                        .filters(filter -> filter.rewritePath("/auth-service/", "/api/v1/"))
                        .uri(authServiceUri))
                .build();
    }
}
