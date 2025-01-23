package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.helper.buildNewBook
import com.gabryel.mercadolivro.repository.BookRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.SpyK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import kotlin.test.assertNotNull

@ExtendWith(MockKExtension::class)
class BookServiceTest {

    @MockK
    private lateinit var bookRepository: BookRepository

    @InjectMockKs
    @SpyK
    private lateinit var bookService: BookService

    @Test
    fun `Get all books without filter returns PageResponse-BookDetailDTO`() {
        val fakeBooks = listOf(buildNewBook(), buildNewBook(), buildNewBook())
        val fakePageBooks = PageImpl(fakeBooks, PageRequest.of(0, 10), fakeBooks.size.toLong())

        every { bookRepository.findAll(any()) } returns fakePageBooks

        val customersPage = bookService.getAll(null, null)

        assertNotNull(customersPage)
        verify(exactly = 1) { bookRepository.findAll(any()) }
        verify(exactly = 0) { bookRepository.findAllByNameContainsIgnoreCase(any(), any()) }
    }

    @Test
    fun `Get all for name books without filter returns PageResponse-BookDetailDTO`() {
        val fakeBooks = listOf(buildNewBook())
        val fakePageBooks = PageImpl(fakeBooks, PageRequest.of(0, 10), fakeBooks.size.toLong())

        every { bookRepository.findAllByNameContainsIgnoreCase(any(), any()) } returns fakePageBooks

        val customersPage = bookService.getAll(null, "book name")

        assertNotNull(customersPage)
        verify(exactly = 0) { bookRepository.findAll(any()) }
        verify(exactly = 1) { bookRepository.findAllByNameContainsIgnoreCase(any(), any()) }
    }

}