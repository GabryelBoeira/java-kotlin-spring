package com.gabryel.mercadolivro.configuration

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Arrays


@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        val info = Info().title("Mercado Livro API").version("v1")

        return OpenAPI()
            .addSecurityItem(SecurityRequirement().addList("Bearer Authentication", listOf("read", "write")))
            .components(Components().addSecuritySchemes("Bearer Authentication", securityScheme()))
            .info(info)
    }

    @Bean
    fun controllerApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("controller-api")
            .packagesToScan("com.gabryel.mercadolivro.controller")
            .build()
    }

    private fun securityScheme(): SecurityScheme {
        return SecurityScheme()
            .name("bearerAuth")
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
    }
}