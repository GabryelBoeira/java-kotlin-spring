package com.gabryel.mercadolivro.repository

import com.gabryel.mercadolivro.enums.BookStatus
import com.gabryel.mercadolivro.model.BookModel
import com.gabryel.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<BookModel, Long> {

    /**
     * Finds all books with the given name.
     *
     * @param name the name of the books to find.
     * @return a list of [BookModel]s with the specified name.
     */
    fun findAllByNameContainsIgnoreCase(name: String): List<BookModel>


    /**
     * Finds all books with the given status.
     *
     * @param active the book status to find.
     * @return a list of [BookModel]s with the specified status.
     */
    fun findAllByStatus(active: BookStatus): List<BookModel>


<<<<<<<<<<<<<<  ✨ Codeium Command ⭐ >>>>>>>>>>>>>>>>
    /**
     * Finds all books belonging to a customer.
     *
     * @param id the customer's ID.
     * @return a list of [BookModel]s belonging to the customer.
     */
<<<<<<<  e775f3b8-5ce1-445a-b750-06e26263a250  >>>>>>>
    fun findAllByCustomerId(id: Long) : List<BookModel>

}