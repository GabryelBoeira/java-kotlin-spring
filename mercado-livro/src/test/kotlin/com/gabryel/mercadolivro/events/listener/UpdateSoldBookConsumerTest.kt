package com.gabryel.mercadolivro.events.listener

import com.gabryel.mercadolivro.events.PurchaseEventProducer
import com.gabryel.mercadolivro.events.UpdateSoldBookConsumer
import com.gabryel.mercadolivro.helper.buildBook
import com.gabryel.mercadolivro.helper.buildPurchase
import com.gabryel.mercadolivro.service.BookService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.*

@ExtendWith(MockKExtension::class)
class UpdateSoldBookConsumerTest {

    @MockK
    private lateinit var bookService: BookService

    @InjectMockKs
    private lateinit var updateSoldBookConsumer: UpdateSoldBookConsumer

    @Test
    fun `Check updating a book's sale`() {
        val bookList = mutableListOf(buildBook(), buildBook(), buildBook())
        val purchase = buildPurchase(nfe = null, books = bookList)

        val fakeNfe = UUID.randomUUID()
        mockkStatic(UUID::class)

        every { UUID.randomUUID() } returns fakeNfe
        every { bookService.updateSoldBook(bookList) } just runs

        updateSoldBookConsumer.listen(PurchaseEventProducer(this, purchase))

        verify(exactly = 1) { bookService.updateSoldBook(bookList) }
    }

}