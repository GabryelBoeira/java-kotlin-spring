package com.gabryel.mercadolivro.dto.book

import java.math.BigDecimal

data class BookDetailDTO(
    val id: Long,
    val name: String,
    val price: BigDecimal
)
