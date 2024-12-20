package com.gabryel.mercadolivro.model

import com.gabryel.mercadolivro.enums.CustomerStatus
import jakarta.persistence.*

@Entity(name = "customer")
data class CustomerModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var email: String,

    @Column
    @Enumerated(EnumType.STRING)
    var status: CustomerStatus
)
