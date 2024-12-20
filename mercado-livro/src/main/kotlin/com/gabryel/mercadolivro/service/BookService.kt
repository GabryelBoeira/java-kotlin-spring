package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.book.BookDetailDTO
import com.gabryel.mercadolivro.model.BookModel
import com.gabryel.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class BookService(
    val bookRepository: BookRepository
) {


    /**
     * Gets all books, optionally filtered by name.
     *
     * @param name the name to filter by, or null to get all books.
     * @return a list of [BookDetailDTO]s.
     */
    fun getAll(name: String?): List<BookDetailDTO> {
        return listOf()
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

        return BookDetailDTO(1, "Livro", BigDecimal(10.0))
    }

    /**
     * Saves a new book to the repository.
     *
     * @param book the [BookModel] to save.
     */
    fun save(book: BookModel) {
        bookRepository.save(book)
    }

    /**
     * Updates a book by its ID.
     *
     * @param id the book's ID.
     * @param update the book's updated data.
     * @throws Exception if the book is not found.
     */
    fun update(id: Long, update: BookModel) {
        if (!bookRepository.existsById(id))
            throw Exception("Book not found")


    }

    /**
     * Deletes a book by its ID.
     *
     * @param id the book's ID.
     * @throws Exception if the book is not found.
     */
    fun delete(id: Long) {
        if (!bookRepository.existsById(id))
            throw Exception("Book not found")

        bookRepository.deleteById(id)
    }

}