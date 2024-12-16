package com.gabryel.mercadolivro.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CustomerUpdateDTO(

    @NotNull
    var name: String,

    @Email
    var email: String
)