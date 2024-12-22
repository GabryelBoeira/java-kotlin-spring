package com.gabryel.mercadolivro.configuration.secutity

import com.gabryel.mercadolivro.repository.CustomerRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.core.GrantedAuthorityDefaults
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig (
    private val customerRepository: CustomerRepository,
    private val authenticationConfiguration: AuthenticationConfiguration
) {

    private val PUBLIC_POST_MATCHERS = arrayOf("/customers", "/books")
    private val PUBLIC_GET_MATCHERS = arrayOf("/customers", "/books")
    private val PUBLIC_MATCHERS = arrayOf("/login")


    @Bean
    @Throws(Exception::class)
    fun securityFilter(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { obj: CsrfConfigurer<HttpSecurity> -> obj.disable() }
            .authorizeHttpRequests(Customizer {
                it
                    .requestMatchers(*PUBLIC_MATCHERS).permitAll()
                    .requestMatchers(HttpMethod.GET, *PUBLIC_GET_MATCHERS).permitAll()
                    .requestMatchers(HttpMethod.POST, *PUBLIC_POST_MATCHERS).permitAll()
                    .anyRequest().authenticated()
            })
            .httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults())
            .addFilter(AuthenticationFilter(authenticationManager(), customerRepository))
            .build()
    }

    @Bean
    fun grantedAuthorityDefaults(): GrantedAuthorityDefaults {
        return GrantedAuthorityDefaults("")
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user = User
            .withUsername("user")
            .password(passwordEncoder().encode("user"))
            .roles("USER")
            .build()

        val admin = User
            .withUsername("admin")
            .password(passwordEncoder().encode("admin"))
            .roles("USER", "ADMIN")
            .build()

        return InMemoryUserDetailsManager(user, admin)
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder(12) // Adjust strength as needed
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

}