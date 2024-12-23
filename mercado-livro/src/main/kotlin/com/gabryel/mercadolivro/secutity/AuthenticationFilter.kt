package com.gabryel.mercadolivro.secutity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gabryel.mercadolivro.dto.login.UserLogin
import com.gabryel.mercadolivro.exception.AuthException
import com.gabryel.mercadolivro.repository.CustomerRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.token.TokenService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class AuthenticationFilter(
    authenticationManager: AuthenticationManager,
    private val customerRepository: CustomerRepository
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val login = jacksonObjectMapper().readValue(request.inputStream, UserLogin::class.java)
            val id = customerRepository.findByEmail(login.username)?.id
            val token = UsernamePasswordAuthenticationToken(id, login.password)

            return authenticationManager.authenticate(token)
        } catch (e: Exception) {
            throw AuthException(e.message.toString(), "invalid.login")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val id = (authResult.principal as CustomUserDetails).id

        response.addHeader("id", id.toString())
    }

}