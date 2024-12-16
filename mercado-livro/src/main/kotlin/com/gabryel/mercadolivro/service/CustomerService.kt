package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.CustomerDetailDTO
import com.gabryel.mercadolivro.dto.CustomerSaveDTO
import com.gabryel.mercadolivro.dto.CustomerUpdateDTO
import org.springframework.stereotype.Service

@Service
class CustomerService {

    /**
     * Gets all customers, optionally filtered by name.
     *
     * @param name the name to filter by, or null to get all customers.
     * @return a list of [CustomerDetailDTO]s.
     */
    fun getAll(name: String?): List<CustomerDetailDTO> {
        return listOf(
            CustomerDetailDTO("123", "teste 1", "teste1@gmail.com"),
            CustomerDetailDTO("456", "teste 2", "teste2@gmail.com")
        )
    }

    /**
     * Gets a customer by their ID.
     *
     * @param id the customer's ID, or null to get all customers.
     * @return a [CustomerDetailDTO].
     */
    fun getById(id: String?): CustomerDetailDTO {
        return CustomerDetailDTO("123", "teste 1", "teste1@gmail.com")
    }

    /**
     * Saves a new customer.
     *
     * @param customer the customer to save.
     */
    fun save(customer: CustomerSaveDTO) {
        TODO("Not yet implemented")
    }

    /**
     * Updates a customer's status by their ID.
     *
     * @param id the customer's ID.
     */
    fun pacth(id: String) {
        TODO("Not yet implemented")
    }

    /**
     * Updates a customer by their ID.
     *
     * @param id the customer's ID.
     * @param update the customer's updated data.
     */
    fun update(id: String, update: CustomerUpdateDTO) {
        TODO("Not yet implemented")
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the customer's ID.
     */
    fun delete(id: String) {
        TODO("Not yet implemented")
    }

}