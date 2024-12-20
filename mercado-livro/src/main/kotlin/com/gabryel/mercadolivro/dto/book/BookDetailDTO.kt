package com.gabryel.mercadolivro.dto.book

import com.gabryel.mercadolivro.dto.customer.CustomerDetailDTO
import com.gabryel.mercadolivro.enums.BookStatus
import java.math.BigDecimal

data class BookDetailDTO(
    val id: Long?,
    val name: String,
    val price: BigDecimal,
    var status: BookStatus?,
    var customer: CustomerDetailDTO?
)
