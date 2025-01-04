package com.gabryel.mercadolivro.extension

import com.gabryel.mercadolivro.dto.book.BookDetailDTO
import com.gabryel.mercadolivro.dto.book.BookSaveDTO
import com.gabryel.mercadolivro.dto.book.BookUpdateDTO
import com.gabryel.mercadolivro.dto.customer.CustomerDetailDTO
import com.gabryel.mercadolivro.dto.customer.CustomerSaveDTO
import com.gabryel.mercadolivro.dto.customer.CustomerUpdateDTO
import com.gabryel.mercadolivro.dto.page.PageResponse
import com.gabryel.mercadolivro.dto.purchase.PurchasesDetailDTO
import com.gabryel.mercadolivro.enums.BookStatus
import com.gabryel.mercadolivro.enums.CustomerStatus
import com.gabryel.mercadolivro.model.BookModel
import com.gabryel.mercadolivro.model.CustomerModel
import com.gabryel.mercadolivro.model.PurchaseModel
import org.springframework.data.domain.Page

/**
 * Converts this [CustomerDetailDTO] into a [CustomerModel].
 *
 * @return a new [CustomerModel] with the same ID, name and email as this [CustomerDetailDTO].
 */
fun CustomerModel.toCustomerDetailDTO() = CustomerDetailDTO(id, name, email, status)

/**
 * Converts this [CustomerSaveDTO] into a [CustomerModel].
 *
 * @return a new [CustomerModel] with the same name and email as this [CustomerSaveDTO].
 */
fun CustomerSaveDTO.toCustomerModel() =
    CustomerModel(name = name, email = email, status = CustomerStatus.ACTIVE, password = password)


/**
 * Converts this [CustomerUpdateDTO] into a [CustomerModel].
 *
 * @return a new [CustomerModel] with the same name and email as this [CustomerUpdateDTO].
 */
fun CustomerUpdateDTO.toCustomerModel(oldCustomer: CustomerModel): CustomerModel {
    return CustomerModel(
        id = oldCustomer.id,
        name = this.name,
        email = this.email,
        status = oldCustomer.status,
        password = oldCustomer.password,
        roles = oldCustomer.roles
    )
}

/**
 * Converts this [BookSaveDTO] into a [BookModel].
 *
 * @param customer the customer the book belongs to.
 *
 * @return a new [BookModel] with the same name, price and status as this [BookSaveDTO],
 * and the customer set to the given [CustomerDetailDTO].
 */
fun BookSaveDTO.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ACTIVE,
        customer = customer
    )
}

/**
 * Converts this [BookModel] into a [BookDetailDTO].
 *
 * @return a new [BookDetailDTO] with the same ID, name, price and status as this [BookModel].
 */
fun BookModel.toBookDetailDTO() = BookDetailDTO(id, name, price, status, customer?.toCustomerDetailDTO())

/**
 * Converts this [BookUpdateDTO] into a [BookModel].
 *
 * @param oldBook the book to update.
 *
 * @return a new [BookModel] with the same ID, name, price and status as [oldBook],
 * but with the name and price potentially updated from this [BookUpdateDTO].
 */
fun BookUpdateDTO.toBookModel(oldBook: BookModel): BookModel {
    return BookModel(
        id = oldBook.id,
        name = this.name ?: oldBook.name,
        price = this.price ?: oldBook.price,
        status = oldBook.status,
        customer = oldBook.customer
    )
}

/**
 * Converts this [PurchaseModel] into a [PurchasesDetailDTO].
 *
 * @return a new [PurchasesDetailDTO] with the same ID, NFE, price, creation date and customer as this [PurchaseModel].
 */
fun PurchaseModel.toPurchaseDetailDTO() = PurchasesDetailDTO(id, nfe, price, createdAt, customer.toCustomerDetailDTO(), books.map { it.toBookDetailDTO() })

/**
 * Converts this [Page] into a [PageResponse].
 *
 * @return a new [PageResponse] containing the same data as this [Page].
 */
fun <T> Page<T>.toPageResponse(): PageResponse<T> {
    return PageResponse(
        this.content,
        this.number,
        this.totalPages,
        this.totalElements,
        this.size)
}
