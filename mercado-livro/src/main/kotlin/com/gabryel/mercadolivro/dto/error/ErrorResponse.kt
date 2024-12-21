package com.gabryel.mercadolivro.dto.error

data class ErrorResponse(
    var httpCode: Int? = null,
    var message: String? = null,
    var internalCode: String? = null,
    var errors: List<FieldErrorResponse>? = null
)