package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.customer.CustomerDetailDTO
import com.gabryel.mercadolivro.dto.customer.CustomerSaveDTO
import com.gabryel.mercadolivro.dto.customer.CustomerUpdateDTO
import com.gabryel.mercadolivro.dto.page.PageResponse
import com.gabryel.mercadolivro.extension.toCustomerModel
import com.gabryel.mercadolivro.secutity.UserCanOnlyAccessTheirOwnResource
import com.gabryel.mercadolivro.service.BookService
import com.gabryel.mercadolivro.service.CustomerService
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController(
    private val customerService: CustomerService,
    private val bookService: BookService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCustomers(@PageableDefault(page = 0, size = 10) pageable: Pageable, @RequestParam name: String?): PageResponse<CustomerDetailDTO> {
        return customerService.getAll(pageable, name)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @UserCanOnlyAccessTheirOwnResource
    fun getCustomer(@PathVariable id: Long): CustomerDetailDTO {
        return customerService.getByIdCustomerDTO(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCustomer(@Valid @RequestBody customer: CustomerSaveDTO) {
        customerService.save(customer.toCustomerModel())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @UserCanOnlyAccessTheirOwnResource
    fun updateCustomer(@PathVariable id: Long, @Valid @RequestBody update: CustomerUpdateDTO) {
        customerService.update(id, update)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: Long) {
        customerService.delete(id)
        bookService.deleteByCustomerId(id)
    }

}