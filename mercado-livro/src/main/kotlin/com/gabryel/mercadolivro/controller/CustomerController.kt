package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.CustomerDetailDTO
import com.gabryel.mercadolivro.dto.CustomerSaveDTO
import com.gabryel.mercadolivro.dto.CustomerUpdateDTO
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCustomers(): List<CustomerDetailDTO> {
        return listOf(
            CustomerDetailDTO("123", "teste 1", "teste1@gmail.com"),
            CustomerDetailDTO("456", "teste 2", "teste2@gmail.com")
        )
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getCustomer(@PathVariable id: String): CustomerDetailDTO {
        return CustomerDetailDTO("123", "1233", "teste@gmail.com")
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveCustomer(@Valid @RequestBody customer: CustomerSaveDTO) {
        println("salvou")
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustomer(@PathVariable id: String){
        println("Pacth")
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateStatus(@PathVariable id: String) {
        println("Pacth")
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateCustomer(@PathVariable id: String, @Valid @RequestBody update: CustomerUpdateDTO) {
        println("atualizar")
    }

}