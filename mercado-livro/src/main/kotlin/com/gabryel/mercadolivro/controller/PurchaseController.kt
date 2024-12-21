package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.purchase.CreatePurchaseRequest
import com.gabryel.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/purchases")
class PurchaseController(
    private val purchaseService: PurchaseService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody puchase : CreatePurchaseRequest) {
        purchaseService.create(puchase)
    }

}

