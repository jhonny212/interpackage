package com.interpackage.apigateway.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AddRoles implements GatewayFilter {
    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // Obtener la solicitud HTTP original y sus cabeceras
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst("Authorization").replace("Bearer ", "");

        try {
            // Decodificar el token y obtener los roles
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            // Obtener la lista de roles
            List<String> nuevosRoles = (List<String>) claims.get("roles");

            // Crear un nuevo objeto HttpHeaders con las cabeceras originales y la cabecera "roles" actualizada
            HttpHeaders modifiedHeaders = new HttpHeaders();
            modifiedHeaders.addAll(headers);
            modifiedHeaders.set("roles", String.join(",", nuevosRoles));

            // Crear una copia mutable del intercambio y actualizar la solicitud original con las nuevas cabeceras
            ServerWebExchange modifiedExchange = exchange.mutate()
                    .request(request.mutate()
                            .headers(httpHeaders -> httpHeaders.addAll(modifiedHeaders))
                            .build())
                    .build();

            // Pasar el intercambio modificado por el filtro hacia el siguiente filtro en la cadena
            return chain.filter(modifiedExchange);

        } catch (Exception e) {
            // Capturar cualquier excepción y devolver un código de respuesta 404
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            return exchange.getResponse().setComplete();
        }
    }
}