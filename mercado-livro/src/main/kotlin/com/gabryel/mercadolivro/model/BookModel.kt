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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null
) {

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.DELETED || field == BookStatus.CANCELLED)
                throw Exception("Book cannot be update status to $field")
            field = value
        }

    constructor(
        id: Long? = null,
        name: String?, price:
        BigDecimal?,
        status: BookStatus?,
        customer: CustomerModel?
    ) : this(id = id, name = name ?: "", price = price ?: BigDecimal.ZERO, customer = customer) {
        this.status = status ?: this.status
    }
}
