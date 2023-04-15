package com.interpackage.apigateway.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;


@Component
public class ValidateToken implements GatewayFilter {
    private final WebClient webClient;
    @Autowired
    private DiscoveryClient discoveryClient;


    public ValidateToken(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Obtener la solicitud HTTP original y sus cabeceras
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("Authorization");
        return webClient.get()
                .uri(getuserServiceUri()+"/api/users/v1/verify-token") //Modificación del URI para comunicarse con USER-MICROSERVICE en la ruta /validar
                .headers(httpHeaders -> httpHeaders.addAll(headers))
                .exchange()
                .flatMap(response -> {
                    HttpStatus status = (HttpStatus) response.statusCode();
                    if (status.is4xxClientError()) {
                        exchange.getResponse().setStatusCode(status);
                        return exchange.getResponse().setComplete();
                    } else {
                        return chain.filter(exchange);
                    }
                });
    }

    public String getuserServiceUri() {
        List<ServiceInstance> instances = discoveryClient.getInstances("users-microservice");
        if (instances.isEmpty()) {
            throw new RuntimeException("No se encontró ninguna instancia de users-microservice");
        }
        String uri = instances.get(0).getUri().toString();
        return uri;
    }
}