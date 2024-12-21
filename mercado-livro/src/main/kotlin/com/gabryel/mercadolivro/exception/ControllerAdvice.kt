package com.gabryel.mercadolivro.exception

import com.gabryel.mercadolivro.dto.error.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            400,
            "Bad Request",
            exception.stackTrace.toString(),
            null)

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}