package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.CustomerDetailDTO
import com.gabryel.mercadolivro.dto.CustomerSaveDTO
import com.gabryel.mercadolivro.dto.CustomerUpdateDTO
import com.gabryel.mercadolivro.extension.toCustomerModel
import com.gabryel.mercadolivro.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
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
    val customerService: CustomerService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCustomers(@RequestParam name: String?): List<CustomerDetailDTO> {
        return customerService.getAll(name)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getCustomer(@PathVariable id: String): CustomerDetailDTO {
        return customerService.getById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCustomer(@Valid @RequestBody customer: CustomerSaveDTO) {
        customerService.save(customer.toCustomerModel())
        println("salvou")
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateStatus(@PathVariable id: String) {
        customerService.pacth(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: String, @Valid @RequestBody update: CustomerUpdateDTO) {
        customerService.update(id, update.toCustomerModel())
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: String) {
        customerService.delete(id)
    }

}