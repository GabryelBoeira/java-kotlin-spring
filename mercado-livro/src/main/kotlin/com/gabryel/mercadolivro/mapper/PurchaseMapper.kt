package com.gabryel.mercadolivro.mapper

import com.gabryel.mercadolivro.dto.purchase.CreatePurchaseRequest
import com.gabryel.mercadolivro.exception.NotFoundException
import com.gabryel.mercadolivro.extension.toCustomerModel
import com.gabryel.mercadolivro.model.PurchaseModel
import com.gabryel.mercadolivro.service.BookService
import com.gabryel.mercadolivro.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    fun toModel(purchase: CreatePurchaseRequest): PurchaseModel {
        val customer = customerService.getByIdCustomerModel(purchase.customerId)
        val books = bookService.findAllById(purchase.bookIds)

        return when {
            books.isEmpty() -> throw NotFoundException("Lista de livros não pode ser vazia", "Books not found")
            else -> {
                PurchaseModel (
                    customer = customer,
                    books = books.toMutableList(),
                    price = books.sumOf { it.price },
                    nfe = ""
                )
            }
        }
    }

}