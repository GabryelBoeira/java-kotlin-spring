package com.gabryel.mercadolivro.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.gabryel.mercadolivro.dto.customer.CustomerSaveDTO
import com.gabryel.mercadolivro.helper.buildCustomer
import com.gabryel.mercadolivro.repository.CustomerRepository
import com.gabryel.mercadolivro.secutity.CustomUserDetails
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import kotlin.random.Random

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
@WithMockUser
class CustomerControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    @AfterEach
    fun tearDown() = customerRepository.deleteAll()

    @Test
    fun `should return all customers`() {
        val customer1 = customerRepository.save(buildCustomer())
        val customer2 = customerRepository.save(buildCustomer())

        mockMvc.perform(get("/customers"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.items[0].id").value(customer1.id))
            .andExpect(jsonPath("$.items[0].name").value(customer1.name))
            .andExpect(jsonPath("$.items[0].email").value(customer1.email))
            .andExpect(jsonPath("$.items[0].status").value(customer1.status.name))
            .andExpect(jsonPath("$.items[1].id").value(customer2.id))
            .andExpect(jsonPath("$.items[1].name").value(customer2.name))
            .andExpect(jsonPath("$.items[1].email").value(customer2.email))
            .andExpect(jsonPath("$.items[1].status").value(customer2.status.name))
    }

    @Test
    fun `should filter all customers by name when get all`() {
        val customer1 = customerRepository.save(buildCustomer(name = "Gustavo"))
        customerRepository.save(buildCustomer(name = "Daniel"))

        mockMvc.perform(get("/customers?name=Gus"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.items.length()").value(1))
            .andExpect(jsonPath("$.items[0].id").value(customer1.id))
            .andExpect(jsonPath("$.items[0].name").value(customer1.name))
            .andExpect(jsonPath("$.items[0].email").value(customer1.email))
            .andExpect(jsonPath("$.items[0].status").value(customer1.status.name))
    }


    @Test
    fun `should create customer`() {
        val request = CustomerSaveDTO("fake name", "${Random.nextInt()}@fakeemail.com", "123456")

        mockMvc.perform(post("/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated)

        val customers = customerRepository.findAll().toList()
        assertEquals(1, customers.size)
        assertEquals(request.name, customers[0].name)
        assertEquals(request.email, customers[0].email)
    }

    @Test
    fun `should get user by id when user has the same id`() {
        val customer = customerRepository.save(buildCustomer())

        mockMvc.perform(get("/customers/${customer.id}").with(user(CustomUserDetails(customer))))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(customer.id))
            .andExpect(jsonPath("$.name").value(customer.name))
            .andExpect(jsonPath("$.email").value(customer.email))
            .andExpect(jsonPath("$.status").value(customer.status.name))
    }

    @Test
    fun `should return forbidden when user has different id`() {
        val customer = customerRepository.save(buildCustomer())

        mockMvc.perform(get("/customers/0")
            .with(user(CustomUserDetails(customer))))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.httpCode").value(402))
            .andExpect(jsonPath("$.message").value("Access Denied"))
            .andExpect(jsonPath("$.internalCode").value("cannot.access.resource"))
    }


}