package com.gabryel.mercadolivro.repository

import com.gabryel.mercadolivro.model.CustomerModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : CrudRepository<CustomerModel, Long> {

    /**
     * Finds all customers with the given name.
     *
     * @param name the name of the customers to find.
     * @return a list of customers with the specified name.
     */
    fun findAllByNameContainsIgnoreCase(name: String): List<CustomerModel>

}