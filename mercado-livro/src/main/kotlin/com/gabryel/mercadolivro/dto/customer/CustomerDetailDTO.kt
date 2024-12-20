package com.gabryel.mercadolivro.dto.customer

import com.gabryel.mercadolivro.enums.CustomerStatus

data class CustomerDetailDTO(
    var id: Long?,
    var name: String,
    var email: String,
    var status: CustomerStatus
)
