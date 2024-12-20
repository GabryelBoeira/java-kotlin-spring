package com.gabryel.mercadolivro.model

import com.gabryel.mercadolivro.enums.BookStatus
import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "book")
data class BookModel(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null

)
