package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.CustomerDetailDTO
import com.gabryel.mercadolivro.dto.CustomerSaveDTO
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customers")
class CustomerController {

    @GetMapping
    fun getAllCustomers(): List<CustomerDetailDTO> {
        return listOf(CustomerDetailDTO("123", "teste 1", "teste1@gmail.com"),
                      CustomerDetailDTO("456", "teste 2", "teste2@gmail.com"))
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable id: String): CustomerDetailDTO {

        return CustomerDetailDTO("123", "1233", "teste@gmail.com")
    }

    @PostMapping
    fun saveCustomer(@Valid @RequestBody customer: CustomerSaveDTO): ResponseEntity<CustomerDetailDTO> {
        return ResponseEntity.ok(CustomerDetailDTO("123", "1233", "teste@gmail.com"))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: String): ResponseEntity<Void>{
        return ResponseEntity.accepted().build()
    }

    @PatchMapping("/{id}")
    fun updateStatus(@PathVariable id: String) : ResponseEntity<Void>{
        return ResponseEntity.accepted().build()
    }

    @PutMapping("/{id}")
    fun updateCustomer(@PathVariable id: String) : ResponseEntity<Void>{
        return ResponseEntity.accepted().build()
    }
    
}