package com.gabryel.mercadolivro.enums

enum class ErrorsCode(val code: String, val message: String) {
    CUSTOMER_NOT_FOUND("ml-[%s]-customer_not_found", "Customer not found for [%s]"),
    BOOK_NOT_FOUND("ml-[%s]-book_not_found", "Book not found for [%s]"),
    BOOK_DELETED_OR_CANCELLED("BOOK_NOT_UPDATABLE_OR_DELETED_OR_CANCELLED", "Book cannot be update status to [%s]"),
    USER_UNAUTHORIZED("UNAUTHORIZED", "User Unauthorized access to system")
}