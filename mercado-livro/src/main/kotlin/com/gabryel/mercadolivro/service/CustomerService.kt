package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.CustomerDetailDTO
import com.gabryel.mercadolivro.extension.toCustomerDetailDTO
import com.gabryel.mercadolivro.model.CustomerModel
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
            CustomerModel("123", "teste 1", "teste1@gmail.com").toCustomerDetailDTO(),
            CustomerModel("456", "teste 2", "teste2@gmail.com").toCustomerDetailDTO()
        )
    }

    /**
     * Gets a customer by their ID.
     *
     * @param id the customer's ID, or null to get all customers.
     * @return a [CustomerDetailDTO].
     */
    fun getById(id: String?): CustomerDetailDTO {
        return CustomerModel("123", "teste 1", "teste1@gmail.com").toCustomerDetailDTO()
    }

    /**
     * Saves a new customer.
     *
     * @param customer the customer[CustomerModel] to save.
     */
    fun save(customer: CustomerModel) {
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
    fun update(id: String, update: CustomerModel) {
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