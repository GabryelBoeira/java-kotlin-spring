package com.gabryel.mercadolivro.dto.purchase

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class CreatePurchaseRequest(

    @field: NotNull
    @field: Positive
    val customerId: Long,

    @field: NotNull
    val bookIds: Set<Long>

)
