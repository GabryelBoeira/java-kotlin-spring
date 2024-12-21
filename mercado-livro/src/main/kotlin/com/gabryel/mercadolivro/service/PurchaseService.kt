package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.CreatePurchaseRequest
import com.gabryel.mercadolivro.mapper.PurchaseMapper
import com.gabryel.mercadolivro.repository.PurchaseRepository
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val purchaseMapper: PurchaseMapper
) {

    /**
     * Creates a new purchase with the given information.
     *
     * @param purchase the request containing the data for the new purchase.
     */
    fun create(purchase: CreatePurchaseRequest) {
        purchaseRepository.save(purchaseMapper.toModel(purchase))
    }

}