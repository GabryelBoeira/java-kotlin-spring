package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.purchase.CreatePurchaseRequest
import com.gabryel.mercadolivro.events.PurchaseEventProducer
import com.gabryel.mercadolivro.helper.buildPurchase
import com.gabryel.mercadolivro.mapper.PurchaseMapper
import com.gabryel.mercadolivro.repository.PurchaseRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.context.ApplicationEventPublisher
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class PurchaseServiceTest {

    @MockK
    private lateinit var purchaseRepository: PurchaseRepository

    @MockK
    private lateinit var applicationEventPublisher: ApplicationEventPublisher

    @MockK
    private val purchaseMapper: PurchaseMapper = mockk()

    @InjectMockKs
    private lateinit var purchaseService: PurchaseService

    val purchaseEventSlot = slot<PurchaseEventProducer>()

    @Test
    fun `should create purchase and publish event`() {
        val purchase = buildPurchase()

        every { purchaseRepository.save(purchase) } returns purchase
        every { purchaseMapper.toModel(any()) } returns purchase
        every { applicationEventPublisher.publishEvent(any()) } just runs

        purchaseService.create(CreatePurchaseRequest(1, setOf(1L, 2L, 3L)))

        verify(exactly = 1) { purchaseRepository.save(purchase) }
        verify(exactly = 1) { applicationEventPublisher.publishEvent(capture(purchaseEventSlot)) }

        assertEquals(purchase, purchaseEventSlot.captured.purchaseModel)
    }

    @Test
    fun `should update purchase`() {
        val purchase = buildPurchase()

        every { purchaseRepository.save(purchase) } returns purchase

        purchaseService.updatePurchaseNfe(purchase)

        verify(exactly = 1) { purchaseRepository.save(purchase) }
    }


}