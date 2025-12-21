package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Server portalServer = new Server()
                .url("https://9534.pro604cr.amypo.ai")
                .description("Portal Gateway Server");

        return new OpenAPI()
                .info(new Info()
                        .title("Digital Asset Lifecycle & Audit Trail API")
                        .version("1.0")
                        .description("API for managing IT assets with full audit trails."))
                .servers(List.of(portalServer));
    }
}