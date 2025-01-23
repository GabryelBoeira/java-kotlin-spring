package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.customer.CustomerUpdateDTO
import com.gabryel.mercadolivro.enums.CustomerStatus
import com.gabryel.mercadolivro.exception.NotFoundException
import com.gabryel.mercadolivro.extension.toCustomerDetailDTO
import com.gabryel.mercadolivro.helper.buildCustomer
import com.gabryel.mercadolivro.helper.buildCustomerList
import com.gabryel.mercadolivro.repository.CustomerRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
class CustomerServiceTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @MockK
    private lateinit var passwordEncoder: BCryptPasswordEncoder

    @InjectMockKs
    @SpyK
    private lateinit var customerService: CustomerService

    @Test
    fun `should return all customers pageable when name is null`() {
        val fakeCustomers = buildCustomerList()
        val fakePageCustomers = PageImpl(fakeCustomers, PageRequest.of(0, 10), fakeCustomers.size.toLong())

        every { customerRepository.findAll(any()) } returns fakePageCustomers

        val customersPage = customerService.getAll(null, null)

        assertNotNull(customersPage)
        verify(exactly = 1) { customerRepository.findAll(any()) }
        verify(exactly = 0) { customerRepository.findAllByNameContainsIgnoreCase(any(), any()) }
    }

    @Test
    fun `should return customers when name is informed`() {
        val fakeCustomers = buildCustomerList()
        val nameCustomer = fakeCustomers[0].name
        val fakePageCustomers = PageImpl(listOf(fakeCustomers[0]), PageRequest.of(0, 10), 1)

        every { customerRepository.findAllByNameContainsIgnoreCase(any(), nameCustomer) } returns fakePageCustomers

        val customers = customerService.getAll(null, nameCustomer)

        assertEquals(nameCustomer, customers.items[0].name)
        verify(exactly = 0) { customerRepository.findAll(null) }
        verify(exactly = 1) { customerRepository.findAllByNameContainsIgnoreCase(null, nameCustomer) }
    }

    @Test
    fun `should create customer and encrypt password`() {
        val initialPassword = Random().nextInt().toString()
        val fakeCustomer = buildCustomer(password = initialPassword)
        val fakePassword = UUID.randomUUID().toString()
        val fakeCustomerEncrypted = fakeCustomer.copy(password = fakePassword)

        every { customerRepository.save(fakeCustomerEncrypted) } returns fakeCustomer
        every { passwordEncoder.encode(initialPassword) } returns fakePassword

        customerService.save(fakeCustomer)

        verify(exactly = 1) { customerRepository.save(fakeCustomerEncrypted) }
        verify(exactly = 1) { passwordEncoder.encode(initialPassword) }
    }

    @Test
    fun `should return customerModel by id`() {
        val id = Random().nextInt().toLong()
        val fakeCustomer = buildCustomer(id = id)

        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)

        val customer = customerService.getByIdCustomerModel(id)

        assertEquals(fakeCustomer, customer)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should throw error when customerModel not found`() {
        val id = Random().nextInt().toLong()

        every { customerRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundException> {
            customerService.getByIdCustomerModel(id)
        }

        assertEquals("Customer not found for [${id}]", error.message)
        assertEquals("ml-[get]-customer_not_found", error.internalCode)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should return customerDetailDTO by id`() {
        val id = Random().nextInt().toLong()
        val fakeCustomer = buildCustomer(id = id)

        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)

        val customerDto = customerService.getByIdCustomerDTO(id)

        assertEquals(fakeCustomer.toCustomerDetailDTO(), customerDto)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should throw error when CustomerDetailDTO not found`() {
        val id = Random().nextInt().toLong()

        every { customerRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundException> {
            customerService.getByIdCustomerDTO(id)
        }

        assertEquals("Customer not found for [${id}]", error.message)
        assertEquals("ml-[get]-customer_not_found", error.internalCode)
        verify(exactly = 1) { customerRepository.findById(id) }
    }

    @Test
    fun `should update customer`() {
        val id = Random().nextInt().toLong()
        val fakeCustomer = buildCustomer(id = id, name = "name", email = "email")

        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)
        every { customerRepository.save(fakeCustomer) } returns fakeCustomer

        customerService.update(id, CustomerUpdateDTO("name", "email"))

        verify(exactly = 1) { customerRepository.findById(id) }
        verify(exactly = 1) { customerRepository.save(fakeCustomer) }
    }

    @Test
    fun `should throw not found exception when update customer`() {
        val id = Random().nextInt().toLong()
        val fakeCustomer = buildCustomer(id = id)

        every { customerRepository.findById(id) } returns Optional.empty()
        every { customerRepository.save(fakeCustomer) } returns fakeCustomer

        val error = assertThrows<NotFoundException>{
            customerService.update(id, CustomerUpdateDTO("name", "email"))
        }

        assertEquals("Customer not found for [${id}]", error.message)
        assertEquals("ml-[update]-customer_not_found", error.internalCode)

        verify(exactly = 1) { customerRepository.findById(id) }
        verify(exactly = 0) { customerRepository.save(any()) }
    }

    @Test
    fun `should delete customer`() {
        val id = Random().nextInt().toLong()
        val fakeCustomer = buildCustomer(id = id)
        val expectedCustomer = fakeCustomer.copy(status = CustomerStatus.INACTIVE)

        every { customerRepository.findById(id) } returns Optional.of(fakeCustomer)
        every { customerRepository.save(expectedCustomer) } returns expectedCustomer

        customerService.delete(id)

        verify(exactly = 1) { customerRepository.findById(id) }
        verify(exactly = 1) { customerRepository.save(expectedCustomer) }
    }

    @Test
    fun `should throw not found exception when delete customer`() {
        val id = Random().nextInt().toLong()

        every { customerRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundException>{ customerService.delete(id) }

        assertEquals("Customer not found for [${id}]", error.message)
        assertEquals("ml-[delete]-customer_not_found", error.internalCode)

        verify(exactly = 1) { customerRepository.findById(id) }
        verify(exactly = 0) { customerRepository.save(any()) }
    }

}