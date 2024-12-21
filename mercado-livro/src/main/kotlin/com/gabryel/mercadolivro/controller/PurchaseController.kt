package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.CreatePurchaseRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/purchases")
class PurchaseController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody puchase : CreatePurchaseRequest ) {

    }

}

