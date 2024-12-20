package com.gabryel.mercadolivro.dto.book

import java.math.BigDecimal

data class BookUpdateDTO(
    val name: String,
    val price: BigDecimal
)
