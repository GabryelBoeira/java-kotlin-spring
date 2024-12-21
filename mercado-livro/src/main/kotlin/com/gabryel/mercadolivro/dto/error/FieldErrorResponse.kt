package com.gabryel.mercadolivro.dto.error

data class FieldErrorResponse(
    var field: String? = null,
    var message: String? = null
)
