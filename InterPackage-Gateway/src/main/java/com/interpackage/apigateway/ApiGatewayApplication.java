package com.interpackage.apigateway;

import com.interpackage.apigateway.filter.AddRoles;
import com.interpackage.apigateway.filter.ValidateToken;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;


@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AddRoles addPermissions, ValidateToken validateToken) {
		return builder.routes()
				//Ruta de autenticacion
				.route("users-microservice-authenticate", r -> r.path("/user/authenticate") //ruta específica para autenticación
						.and().method(HttpMethod.POST) //condición para solicitudes POST
						.filters(f -> f.rewritePath("/user/(?<remaining>.*)", "/api/users/v1/${remaining}"))
						.uri("lb://USERS-MICROSERVICE"))

				//Ruta del microservicio tracking
				.route("tracking-microservice", r -> r.path("/tracking/**")
						.filters(f -> f.rewritePath("/tracking/(?<remaining>.*)", "/api/tracking/v1/${remaining}")

								.filter(validateToken)
								.filter(addPermissions)
						)
						.uri("lb://TRACKING-MICROSERVICE"))

				//Ruta del microservicio usuario
				.route("users-microservice", r -> r.path("/user/**")
						.filters(f -> f.rewritePath("/user/(?<remaining>.*)", "/api/users/v1/${remaining}")

								.filter(validateToken)
								.filter(addPermissions)
						)
						.uri("lb://USERS-MICROSERVICE"))

				.build();
	}

}
