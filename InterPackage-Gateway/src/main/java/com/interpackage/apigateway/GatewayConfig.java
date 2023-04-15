package com.interpackage.apigateway;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.filter.reactive.ServerWebExchangeContextFilter;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig implements WebFluxConfigurer {

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

    @Bean
    public ServerWebExchangeContextFilter serverWebExchangeContextFilter() {
        return new ServerWebExchangeContextFilter();
    }

    @Bean
    public WebFilter corsFilter() {
        return new WebFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
                ServerHttpRequest request = exchange.getRequest();

                HttpHeaders headers = request.getHeaders();
                if (headers.containsKey(HttpHeaders.ORIGIN)) {
                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.setAccessControlAllowOrigin(headers.getOrigin());
                    responseHeaders.setAccessControlAllowCredentials(true);
                    responseHeaders.setAccessControlAllowHeaders(Arrays.asList("Content-Type, Authorization"));
                    responseHeaders.setAccessControlAllowMethods(Arrays.asList(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE));
                    exchange.getResponse().getHeaders().addAll(responseHeaders);
                }

                return chain.filter(exchange);
            }
        };
    }
}