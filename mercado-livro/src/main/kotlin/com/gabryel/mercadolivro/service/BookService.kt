package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.book.BookDetailDTO
import com.gabryel.mercadolivro.dto.book.BookSaveDTO
import com.gabryel.mercadolivro.dto.book.BookUpdateDTO
import com.gabryel.mercadolivro.enums.BookStatus
import com.gabryel.mercadolivro.extension.toBookDetailDTO
import com.gabryel.mercadolivro.extension.toBookModel
import com.gabryel.mercadolivro.model.BookModel
import com.gabryel.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository,
    val customerService: CustomerService
) {

    /**
     * Gets all books, optionally filtered by name.
     *
     * @param name the name to filter by, or null to get all books.
     * @return a list of [BookDetailDTO]s.
     */
    fun getAll(name: String?): List<BookDetailDTO> {
        if (name == null)
            return bookRepository.findAll().map { bk -> bk.toBookDetailDTO() }
        return bookRepository.findAllByNameContainsIgnoreCase(name).map { bk -> bk.toBookDetailDTO() }
    }

    /**
     * Gets a book by its ID.
     *
     * @param id the book's ID.
     * @return a [BookDetailDTO].
     * @throws Exception if the book is not found.
     */
    fun getById(id: Long): BookDetailDTO {
        val book = bookRepository.findById(id)
        if (!book.isPresent)
            throw Exception("Book not found")

        return book.get().toBookDetailDTO()
    }

    fun getByStatusActive(): List<BookDetailDTO> {
        return bookRepository.findAllByStatus(BookStatus.ACTIVE).map { bk -> bk.toBookDetailDTO() }
    }

    /**
     * Saves a new book to the repository.
     *
     * @param book the [BookModel] to save.
     */
    fun save(bookSave: BookSaveDTO) {
        val book = bookSave.toBookModel(customerService.getById(bookSave.customerId))
        bookRepository.save(book)
    }

    /**
     * Updates a book by its ID.
     *
     * @param id the book's ID.
     * @param update the book's updated data.
     * @throws Exception if the book is not found.
     */
    fun update(id: Long, update: BookUpdateDTO) {
        val optBook = bookRepository.findById(id)
        if (!optBook.isPresent)
            throw Exception("Book not found")

        bookRepository.save(update.toBookModel(optBook.get()))
    }

    /**
     * Deletes a book by its ID.
     *
     * @param id the book's ID.
     * @throws Exception if the book is not found.
     */
    fun delete(id: Long) {
        val optBook = bookRepository.findById(id)
        if (!optBook.isPresent)
            throw Exception("Book not found")

        val book = optBook.get()
        book.status = BookStatus.DELETED

        bookRepository.save(book)
    }





}