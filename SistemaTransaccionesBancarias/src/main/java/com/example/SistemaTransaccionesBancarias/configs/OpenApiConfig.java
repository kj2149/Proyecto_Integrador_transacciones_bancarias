package com.example.SistemaTransaccionesBancarias.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title ="Sistema Transacciones Bancarias",
                version = "1.0.0",
                description = "Este es un sistema de gestión para transacciones bancarias"
        )
)
public class OpenApiConfig {
}
