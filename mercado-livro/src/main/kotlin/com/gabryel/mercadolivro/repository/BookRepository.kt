package com.gabryel.mercadolivro.repository

import com.gabryel.mercadolivro.enums.BookStatus
import com.gabryel.mercadolivro.model.BookModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    fun findAllByNameContainsIgnoreCase(pageable: Pageable, name: String): Page<BookModel>

    /**
     * Finds all books with the given status.
     *
     * @param pageable the pagination information.
     * @param active the status of the books to find.
     * @return a list of [BookModel]s with the specified status.
     */
    fun findAllByStatus(pageable: Pageable, active: BookStatus): Page<BookModel>


    /**
     * Finds all books belonging to a customer.
     *
     * @param id the customer's ID.
     * @return a list of [BookModel]s belonging to the customer.
     */
    fun findAllByCustomerId(id: Long) : List<BookModel>

    /**
     * Finds all books with pagination support.
     *
     * @param pageable the pagination information.
     * @return a paginated list of all [BookModel]s.
     */
    fun findAll(pageable: Pageable): Page<BookModel>

}