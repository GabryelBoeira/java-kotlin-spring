package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.enums.CustomerStatus
import com.gabryel.mercadolivro.enums.Role
import com.gabryel.mercadolivro.model.CustomerModel
import com.gabryel.mercadolivro.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.UUID
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var passwordEncoder : BCryptPasswordEncoder

    @InjectMockKs
    private lateinit var customerService: CustomerService

    /**
     * Builds a list of fake customer models for testing purposes.
     *
     * @return a list of 5 [CustomerModel] instances with unique IDs and emails.
     */
    private fun buildCustomerList() : List<CustomerModel> {
        return buildList {
            for (i in 1..5) {
                add(CustomerModel(
                    id = i.toLong(),
                    name = "customer name $i",
                    email = "$i${UUID.randomUUID()}@email.com",
                    status = CustomerStatus.ACTIVE,
                    password = "password$i",
                    roles = setOf(Role.CUSTOMER)
                ))
            }
        }
    }


    @Test
    fun `should return all customers pageable when name is null`() {
        val fakeCustomers = buildCustomerList()
        val fakePageCustomers = PageImpl(fakeCustomers, PageRequest.of(0, 10), fakeCustomers.size.toLong())

        every { customerRepository.findAll(any()) } returns fakePageCustomers

        val customersPage = customerService.getAll(null, null)

        assertNotNull(customersPage)
        verify(exactly = 1) { customerRepository.findAll(any()) }
        verify(exactly = 0) { customerRepository.findAllByNameContainsIgnoreCase(any() ,any()) }
    }


}