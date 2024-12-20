package com.gabryel.mercadolivro.dto.book;

import com.fasterxml.jackson.annotation.JsonAlias
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class BookSaveDTO (
    @field: NotBlank
    val name: String,

    @field: NotNull
    val price: BigDecimal,

    @JsonAlias("customer_id")
    @field: NotNull
    val customerId: Long

)

