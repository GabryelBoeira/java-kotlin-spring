package com.gabryel.mercadolivro.dto.customer

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CustomerSaveDTO(

    @field: NotBlank
    var name: String,

    @field: Email
    var email: String,

    @field: NotBlank
    var password: String
)