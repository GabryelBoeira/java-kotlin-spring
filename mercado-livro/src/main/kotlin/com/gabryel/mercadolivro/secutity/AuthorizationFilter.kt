package com.gabryel.mercadolivro.secutity

import com.gabryel.mercadolivro.exception.AuthException
import com.gabryel.mercadolivro.service.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val jwtUtils: JwtUtils
) : BasicAuthenticationFilter(authenticationManager) {

    /**
     * Filters incoming HTTP requests to check for the presence of an authorization header.
     * If a valid Bearer token is found, it attempts to authenticate the token.
     *
     * @param request the HTTP request to be processed
     * @param response the HTTP response
     * @param filterChain the filter chain to pass the request and response to the next filter
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val authorization = request.getHeader("Autorization")

        if (authorization != null && authorization.startsWith("Bearer ")) {
            val authResult = getAuthentication(authorization.split(" ")[1])
            SecurityContextHolder.getContext().authentication = authResult
        }
        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        if (jwtUtils.isInvalidToken(token))
            throw AuthException("Token inválido", "invalid.token")

        val subject = jwtUtils.getSubject(token)
        val user = userDetailsService.loadUserByUsername(subject)

        return UsernamePasswordAuthenticationToken(subject, null, user.authorities)
    }
}