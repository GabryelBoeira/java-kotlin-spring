package com.gabryel.mercadolivro.service

import com.gabryel.mercadolivro.dto.book.BookDetailDTO
import com.gabryel.mercadolivro.dto.book.BookSaveDTO
import com.gabryel.mercadolivro.dto.book.BookUpdateDTO
import com.gabryel.mercadolivro.enums.BookStatus
import com.gabryel.mercadolivro.enums.ErrorsCode
import com.gabryel.mercadolivro.exception.NotFoundException
import com.gabryel.mercadolivro.extension.toBookDetailDTO
import com.gabryel.mercadolivro.extension.toBookModel
import com.gabryel.mercadolivro.model.BookModel
import com.gabryel.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    fun getAll(pageable: Pageable, name: String?): Page<BookDetailDTO> {
        if (name == null)
            return bookRepository.findAll(pageable).map { bk -> bk.toBookDetailDTO() }
        return bookRepository.findAllByNameContainsIgnoreCase(pageable, name).map { bk -> bk.toBookDetailDTO() }
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
            throw NotFoundException(ErrorsCode.BOOK_NOT_FOUND.message.format(id), ErrorsCode.BOOK_NOT_FOUND.code.format("get"))

        return book.get().toBookDetailDTO()
    }

    fun getByStatusActive(pageable: Pageable): Page<BookDetailDTO> {
        return bookRepository.findAllByStatus(pageable, BookStatus.ACTIVE).map { bk -> bk.toBookDetailDTO() }
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
            throw NotFoundException(ErrorsCode.BOOK_NOT_FOUND.message.format(id), ErrorsCode.BOOK_NOT_FOUND.code.format("update"))

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
            throw NotFoundException(ErrorsCode.BOOK_NOT_FOUND.message.format(id), ErrorsCode.BOOK_NOT_FOUND.code.format("delete"))

        val book = optBook.get()
        book.status = BookStatus.DELETED

        bookRepository.save(book)
    }


    /**
     * Deletes all books belonging to a customer by their ID.
     *
     * @param id the customer's ID.
     */
    fun deleteByCustomerId(id: Long) {
        var books = bookRepository.findAllByCustomerId(id)
        for (book in books) {
            book.status = BookStatus.DELETED
        }
        bookRepository.saveAll(books)
    }

    /**
     * Patches a book's status by its ID.
     *
     * @param id the book's ID.
     * @param status the new status of the book.
     * @throws Exception if the book is not found.
     */
    fun patchStatus(id: Long, status: BookStatus) {
        val optBook = bookRepository.findById(id)
        if (!optBook.isPresent)
            throw NotFoundException(ErrorsCode.BOOK_NOT_FOUND.message.format(id), ErrorsCode.BOOK_NOT_FOUND.code.format("patch"))

        val book = optBook.get()
        book.status = status
        bookRepository.save(book)
    }

}