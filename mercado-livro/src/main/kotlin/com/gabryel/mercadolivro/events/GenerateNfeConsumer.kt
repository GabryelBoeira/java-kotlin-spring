package com.gabryel.mercadolivro.events

import com.gabryel.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import java.util.*

class GenerateNfeConsumer (
    private val purchaseService: PurchaseService
) {

    /**
     * Handles the PurchaseEventPublisher event by generating a random NFe id.
     *
     * @param purchaseEvent the event containing the purchase model.
     */
    @EventListener
    fun listen(purchaseEvent: PurchaseEventProducer) {
        val nfe = UUID.randomUUID().toString()
        val purchase = purchaseEvent.purchaseModel.copy(nfe = nfe)
        purchaseService.updatePurchaseNfe(purchase)
    }

}