package com.gabryel.mercadolivro.events

import com.gabryel.mercadolivro.service.BookService
import com.gabryel.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class UpdateSoldBookConsumer(
    private val bookService: BookService
) {

    /**
     * Handles the PurchaseEventPublisher event by generating a random NFe id.
     *
     * @param purchaseEvent the event containing the purchase model.
     */
    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEventProducer) {
        bookService.updateSoldBook(purchaseEvent.purchaseModel.books)
    }

}