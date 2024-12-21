package com.gabryel.mercadolivro.enums

enum class ErrorsCode(val code: String, val message: String) {
    CUSTOMER_NOT_FOUND("ml-[%s]-customer_not_found", "Customer not found for [%s]"),
    BOOK_NOT_FOUND("ml-[%s]-book_not_found", "Book not found for [%s]");

}