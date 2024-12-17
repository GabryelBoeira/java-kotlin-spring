package com.gabryel.mercadolivro.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CustomerUpdateDTO(

    @field: NotBlank
    var name: String,

    @field: Email
    var email: String
)