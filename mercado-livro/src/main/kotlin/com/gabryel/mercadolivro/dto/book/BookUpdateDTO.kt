package com.gabryel.mercadolivro.dto.book

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class BookUpdateDTO(
    @field: NotBlank
    val name: String?,

    @field: NotNull
    val price: BigDecimal?
)
