package com.gabryel.mercadolivro.helper

import com.gabryel.mercadolivro.enums.CustomerStatus
import com.gabryel.mercadolivro.enums.Role
import com.gabryel.mercadolivro.model.BookModel
import com.gabryel.mercadolivro.model.CustomerModel
import com.gabryel.mercadolivro.model.PurchaseModel
import java.math.BigDecimal
import java.util.*

/**
 * Builds a new [BookModel] instance with the given properties.
 *
 * @param id the ID of the book. If null, a new UUID will be generated.
 * @param name the name of the book. Defaults to "book name".
 * @param price the price of the book. Defaults to 0.
 * @param customerId the ID of the customer the book belongs to. If null, a new UUID will be generated.
 * @return a new [BookModel] instance with the given properties.
 */
fun buildBook(
    id: Long? = null,
    name: String = "book name",
    price: BigDecimal = BigDecimal.ZERO,
    customerId: Long? = null
) = BookModel(
    id = id,
    name = name,
    price = price,
    customer = buildCustomer(id = customerId ?: Random().nextInt().toLong())
)

/**
 * Builds a new [CustomerModel] instance with the given properties.
 *
 * @param id the customer's ID.
 * @param name the customer's name. Defaults to "customer name".
 * @param email the customer's email. Defaults to a random UUID followed by "@email.com".
 * @param password the customer's password. Defaults to "password".
 * @return a new [CustomerModel] instance with the given properties.
 */
fun buildCustomer(
    id: Long? = null,
    name: String = "customer name",
    email: String = "${UUID.randomUUID()}@email.com",
    password: String = "password"
) = CustomerModel(
    id = id,
    name = name,
    email = email,
    status = CustomerStatus.ACTIVE,
    password = password,
    roles = setOf(Role.CUSTOMER)
)


/**
 * Builds a list of [CustomerModel] instances for testing purposes.
 *
 * @param size the desired size of the list. Defaults to 5.
 * @return a list of [CustomerModel] instances with unique IDs and emails.
 */
fun buildCustomerList(size: Int = 5): List<CustomerModel> {
    return buildList {
        for (i in 1..size) {
            add(
                buildCustomer(
                    id = i.toLong(),
                    name = "customer name $i",
                    email = "$i${UUID.randomUUID()}@email.com",
                    password = "password$i",
                )
            )
        }
    }
}


/**
 * Builds a new [PurchaseModel] instance with the given properties.
 *
 * @param id the purchase's ID.
 * @param customer the customer who made the purchase. Defaults to a new [CustomerModel] instance.
 * @param books the list of books purchased. Defaults to an empty mutable list.
 * @param nfe the NFE number for the purchase. Defaults to a random UUID string.
 * @param price the total price of the purchase. Defaults to 10.00.
 * @return a new [PurchaseModel] instance with the given properties.
 */
fun buildPurchase(
    id: Long? = null,
    customer: CustomerModel = buildCustomer(),
    books: MutableList<BookModel> = mutableListOf(),
    nfe: String = UUID.randomUUID().toString(),
    price: BigDecimal = BigDecimal.TEN
) = PurchaseModel(
    id = id,
    customer = customer,
    books = books,
    nfe = nfe,
    price = price
)
