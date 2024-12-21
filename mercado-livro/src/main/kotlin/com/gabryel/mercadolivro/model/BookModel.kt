package com.gabryel.mercadolivro.model

import com.gabryel.mercadolivro.enums.BookStatus
import com.gabryel.mercadolivro.enums.ErrorsCode
import com.gabryel.mercadolivro.exception.BadRequestException
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
                throw BadRequestException(ErrorsCode.BOOK_DELETED_OR_CANCELLED.message.format(field), ErrorsCode.BOOK_DELETED_OR_CANCELLED.code)
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
