package com.gabryel.mercadolivro.dto.customer

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CustomerUpdateDTO(

    @field: NotBlank
    var name: String,

    @field: Email
    var email: String
)