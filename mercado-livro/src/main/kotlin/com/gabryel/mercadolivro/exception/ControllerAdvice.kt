package com.gabryel.mercadolivro.exception

import com.gabryel.mercadolivro.dto.error.ErrorResponse
import com.gabryel.mercadolivro.dto.error.FieldErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleException(exception: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            404,
            exception.message,
            exception.internalCode,
            null)

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleException(exception: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            422,
            exception.body.detail,
            exception.objectName,
            exception.bindingResult.fieldErrors.map { FieldErrorResponse(it.field, it.defaultMessage ?: "Invalid") }
        )

        return ResponseEntity(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleException(exception: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            400,
            exception.message,
            exception.code,
            null
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleException(exception: AccessDeniedException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            402,
            exception.message,
            "cannot.access.resource",
            null
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

}