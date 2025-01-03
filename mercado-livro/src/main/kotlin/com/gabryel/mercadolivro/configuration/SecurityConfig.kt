package com.gabryel.mercadolivro.configuration

import com.gabryel.mercadolivro.repository.CustomerRepository
import com.gabryel.mercadolivro.secutity.AuthenticationFilter
import com.gabryel.mercadolivro.secutity.AuthorizationFilter
import com.gabryel.mercadolivro.secutity.CustomAuthenticationEntryPoint
import com.gabryel.mercadolivro.secutity.JwtUtils
import com.gabryel.mercadolivro.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
class SecurityConfig(
    private val customerRepository: CustomerRepository,
    private val authenticationConfiguration: AuthenticationConfiguration,
    private val userDetails: CustomUserDetailsService,
    private val jwtUtils: JwtUtils,
    private val customEntryPoint: CustomAuthenticationEntryPoint
) {

    private val PUBLIC_POST_MATCHERS = arrayOf("/customers", "/books")
    private val PUBLIC_GET_MATCHERS = arrayOf("/customers", "/books")
    private val PUBLIC_MATCHERS = arrayOf("/login")
    private val ADMIN_MATCHERS = arrayOf("/admin/**", "/actuator/**", "/delete/**")
    private val SWAGGER_MATCHERS = arrayOf("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")


    @Bean
    fun securityFilter(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .authorizeHttpRequests(Customizer {
                it
                    .requestMatchers(*SWAGGER_MATCHERS).permitAll()
                    .requestMatchers(*PUBLIC_MATCHERS).permitAll()
                    .requestMatchers(HttpMethod.GET, *PUBLIC_GET_MATCHERS).permitAll()
                    .requestMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
                    .requestMatchers(*ADMIN_MATCHERS).hasRole("ADMIN")
                    .anyRequest().authenticated()
            })
            .formLogin(Customizer.withDefaults())
            .addFilter(AuthenticationFilter(authenticationManager(), customerRepository, jwtUtils))
            .addFilter(AuthorizationFilter(authenticationManager(), userDetails, jwtUtils))
            .exceptionHandling { obj: ExceptionHandlingConfigurer<HttpSecurity> ->
                obj.authenticationEntryPoint(
                    customEntryPoint
                )
            }
            .sessionManagement { obj: SessionManagementConfigurer<HttpSecurity> ->
                obj.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder(12) // Adjust strength as needed
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    @Primary
    fun AuthenticationManagerBuilder(auth: AuthenticationManagerBuilder): AuthenticationManagerBuilder {
        auth.userDetailsService(userDetails)
            .passwordEncoder(passwordEncoder())
        return auth
    }
}