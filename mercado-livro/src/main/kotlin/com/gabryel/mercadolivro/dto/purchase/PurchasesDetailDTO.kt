package com.gabryel.mercadolivro.dto.purchase

import com.gabryel.mercadolivro.dto.book.BookDetailDTO
import com.gabryel.mercadolivro.dto.customer.CustomerDetailDTO
import java.math.BigDecimal
import java.time.LocalDateTime

data class PurchasesDetailDTO(
    var id: Long?,
    val nfe: String?,
    val price: BigDecimal,
    val createdAt: LocalDateTime,
    val customer: CustomerDetailDTO,
    val books: List<BookDetailDTO>?
)