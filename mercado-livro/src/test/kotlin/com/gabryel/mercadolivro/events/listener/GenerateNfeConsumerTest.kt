package com.gabryel.mercadolivro.events.listener

import com.gabryel.mercadolivro.events.GenerateNfeConsumer
import com.gabryel.mercadolivro.events.PurchaseEventProducer
import com.gabryel.mercadolivro.helper.buildPurchase
import com.gabryel.mercadolivro.service.PurchaseService
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
class GenerateNfeConsumerTest {

    @MockK
    private lateinit var purchaseService: PurchaseService

    @InjectMockKs
    private lateinit var generateNfeConsumer: GenerateNfeConsumer

    @Test
    fun `should generate nfe`() {
        val purchase = buildPurchase(nfe = null)
        val fakeNfe = UUID.randomUUID()
        val purchaseExpected = purchase.copy(nfe = fakeNfe.toString())
        mockkStatic(UUID::class)

        every { UUID.randomUUID() } returns fakeNfe
        every { purchaseService.updatePurchaseNfe(purchaseExpected) } just  runs

        generateNfeConsumer.listen(PurchaseEventProducer(this, purchase))

        verify(exactly = 1) { purchaseService.updatePurchaseNfe(purchaseExpected) }
    }

}