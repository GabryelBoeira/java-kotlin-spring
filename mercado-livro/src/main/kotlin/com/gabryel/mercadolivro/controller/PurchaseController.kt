package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.book.BookDetailDTO
import com.gabryel.mercadolivro.dto.page.PageResponse
import com.gabryel.mercadolivro.dto.purchase.CreatePurchaseRequest
import com.gabryel.mercadolivro.dto.purchase.PurchasesDetailDTO
import com.gabryel.mercadolivro.service.PurchaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/purchases")
class PurchaseController(
    private val purchaseService: PurchaseService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun findAllPurchases(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<PurchasesDetailDTO> {
        return purchaseService.findAllPageable(pageable)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody puchase : CreatePurchaseRequest) {
        purchaseService.create(puchase)
    }

}

