package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.customer.CustomerDetailDTO
import com.gabryel.mercadolivro.dto.customer.CustomerUpdateDTO
import com.gabryel.mercadolivro.dto.page.PageResponse
import com.gabryel.mercadolivro.enums.CustomerStatus
import com.gabryel.mercadolivro.enums.ErrorsCode
import com.gabryel.mercadolivro.enums.Role
import com.gabryel.mercadolivro.exception.NotFoundException
import com.gabryel.mercadolivro.extension.toCustomerDetailDTO
import com.gabryel.mercadolivro.extension.toCustomerModel
import com.gabryel.mercadolivro.extension.toPageResponse
import com.gabryel.mercadolivro.model.CustomerModel
import com.gabryel.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val passwordEncoder : BCryptPasswordEncoder
) {

    /**
     * Gets all customers, optionally filtered by name.
     *
     * @param name the name to filter by, or null to get all customers.
     * @return a list of [CustomerDetailDTO]s.
     */
    fun getAll(pageable : Pageable, name: String?): PageResponse<CustomerDetailDTO> {
        if (name == null)
            return customerRepository.findAll(pageable).map { cm -> cm.toCustomerDetailDTO() } .toPageResponse()
        return customerRepository.findAllByNameContainsIgnoreCase(pageable, name).map { cm -> cm.toCustomerDetailDTO() } .toPageResponse()
    }

    /**
     * Gets a customer by their ID.
     *
     * @param id the customer's ID.
     * @return a [CustomerDetailDTO].
     * @throws NotFoundException if the customer is not found.
     */
    fun getByIdCustomerDTO(id: Long): CustomerDetailDTO {
        val customer = customerRepository.findById(id)
        if (!customer.isPresent)
            throw NotFoundException(
                ErrorsCode.CUSTOMER_NOT_FOUND.message.format(id),
                ErrorsCode.CUSTOMER_NOT_FOUND.code.format("get")
            )

        return customer.get().toCustomerDetailDTO()
    }

    /**
     * Gets a customer by their ID.
     *
     * @param id the customer's ID.
     * @return a [CustomerModel].
     * @throws NotFoundException if the customer is not found.
     */
    fun getByIdCustomerModel(id: Long): CustomerModel {
        val customer = customerRepository.findById(id)
        if (!customer.isPresent)
            throw NotFoundException(
                ErrorsCode.CUSTOMER_NOT_FOUND.message.format(id),
                ErrorsCode.CUSTOMER_NOT_FOUND.code.format("get")
            )

        return customer.get()
    }

    /**
     * Saves a new customer.
     *
     * @param customer the customer[CustomerModel] to save.
     */
    fun save(customer: CustomerModel) {
        val customerCopy = customer.copy(
            roles = setOf(Role.CUSTOMER),
            password = passwordEncoder.encode(customer.password)
        )

        customerRepository.save(customerCopy)
    }

    /**
     * Updates a customer by their ID.
     *
     * @param id the customer's ID.
     * @param update the customer's updated data.
     */
    fun update(id: Long, update: CustomerUpdateDTO) {
        val customer = customerRepository.findById(id)
        if (!customer.isPresent)
            throw NotFoundException(
                ErrorsCode.CUSTOMER_NOT_FOUND.message.format(id),
                ErrorsCode.CUSTOMER_NOT_FOUND.code.format("update")
            )

        customerRepository.save(update.toCustomerModel(customer.get()))
    }

    /**
     * Deletes a customer by their ID.
     *
     * @param id the customer's ID.
     */
    fun delete(id: Long) {
        val customer = customerRepository.findById(id)
        if (!customer.isPresent)
            throw NotFoundException(
                ErrorsCode.CUSTOMER_NOT_FOUND.message.format(id),
                ErrorsCode.CUSTOMER_NOT_FOUND.code.format("delete")
            )

        val delete = customer.get()
        delete.status = CustomerStatus.INACTIVE
        customerRepository.save(delete)
    }

}