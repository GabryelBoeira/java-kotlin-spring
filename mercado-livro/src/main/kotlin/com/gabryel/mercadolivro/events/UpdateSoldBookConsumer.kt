package com.gabryel.mercadolivro.events

import com.gabryel.mercadolivro.service.BookService
import com.gabryel.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import java.util.*

class UpdateSoldBookConsumer(
    private val bookService: BookService
) {

    /**
     * Handles the PurchaseEventPublisher event by generating a random NFe id.
     *
     * @param purchaseEvent the event containing the purchase model.
     */
    @EventListener
    fun listen(purchaseEvent: PurchaseEventProducer) {
        bookService.updateSoldBook(purchaseEvent.purchaseModel.books)
    }

}