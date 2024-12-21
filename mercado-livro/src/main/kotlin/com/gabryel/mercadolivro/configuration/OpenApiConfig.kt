package com.gabryel.mercadolivro.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.models.GroupedOpenApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(Info()
                .title("Merado Livro API")
                .version("v1")
            )
    }

    @Bean
    fun controllerApi(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("controller-api")
            .packagesToScan("com.gabryel.mercadolivro.controller")
            .build()
    }
}