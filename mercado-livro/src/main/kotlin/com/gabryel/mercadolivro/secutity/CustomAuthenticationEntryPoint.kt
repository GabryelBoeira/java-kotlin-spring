package com.gabryel.mercadolivro.secutity

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.gabryel.mercadolivro.dto.error.ErrorResponse
import com.gabryel.mercadolivro.enums.ErrorsCode
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationEntryPoint : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        authException: AuthenticationException?
    ) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        val errorDetail = ErrorResponse(
            HttpServletResponse.SC_UNAUTHORIZED,
            ErrorsCode.USER_UNAUTHORIZED.message,
            ErrorsCode.USER_UNAUTHORIZED.code,
        )
        response.outputStream.print(jacksonObjectMapper().writeValueAsString(errorDetail))
    }
}