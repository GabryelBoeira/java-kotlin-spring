package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.CreatePurchaseRequest
import com.gabryel.mercadolivro.events.PurchaseEventProducer
import com.gabryel.mercadolivro.exception.BadRequestException
import com.gabryel.mercadolivro.mapper.PurchaseMapper
import com.gabryel.mercadolivro.model.PurchaseModel
import com.gabryel.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val purchaseMapper: PurchaseMapper,
    private val applicationEvent : ApplicationEventPublisher
) {

    /**
     * Creates a new purchase with the given information.
     *
     * @param purchase the request containing the data for the new purchase.
     */
    fun create(purchase: CreatePurchaseRequest) {
        val model = purchaseRepository.save(purchaseMapper.toModel(purchase))

        applicationEvent.publishEvent(PurchaseEventProducer(this, model))
    }


    /**
     * Updates the NFe information of the specified purchase.
     *
     * @param purchase the [PurchaseModel] containing updated NFe data to be saved.
     */
    fun updatePurchaseNfe(purchase: PurchaseModel) {
        if (purchase.nfe == "" || purchase.nfe == null) throw BadRequestException("NFe não pode ser vazia", "Bad NFe")
        purchaseRepository.save(purchase)
    }

}