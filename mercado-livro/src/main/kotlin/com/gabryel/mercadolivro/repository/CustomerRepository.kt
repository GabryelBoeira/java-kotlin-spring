package com.gabryel.mercadolivro.repository

import com.gabryel.mercadolivro.model.CustomerModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<CustomerModel, Long> {


    /**
     * Finds all customers whose names contain the given string, ignoring case.
     *
     * @param pageable the pagination information.
     * @param name the substring to search for within customer names.
     * @return a paginated list of [CustomerModel]s whose names contain the specified substring.
     */
    fun findAllByNameContainsIgnoreCase(pageable: Pageable?, name: String): Page<CustomerModel>

    /**
     * Finds a customer by their email.
     *
     * @param email the customer's email.
     * @return a customer with the specified email.
     */
    fun findByEmail(email: String): CustomerModel?

    /**
     * Finds all customers, with pagination support.
     *
     * @param pageable the pagination information.
     * @return a paginated list of all [CustomerModel]s.
     */
    fun findAll(pageable: Pageable?): Page<CustomerModel>

}