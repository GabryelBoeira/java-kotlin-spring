package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.book.BookDetailDTO
import com.gabryel.mercadolivro.dto.book.BookSaveDTO
import com.gabryel.mercadolivro.dto.book.BookUpdateDTO
import com.gabryel.mercadolivro.service.BookService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController (
  val bookService: BookService,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(@RequestParam name: String?): List<BookDetailDTO> {
        return bookService.getAll(name)
    }

    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    fun getByStatusActive(@RequestParam name: String?): List<BookDetailDTO> {
        return bookService.getByStatusActive()
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getOne(@PathVariable id: Long): BookDetailDTO {
        return bookService.getById(id)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save(@Valid @RequestBody book: BookSaveDTO) {
        bookService.save(book)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun update(@PathVariable id: Long, @Valid @RequestBody book: BookUpdateDTO) {
        bookService.update(id,book)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        bookService.delete(id)
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateStatus(@PathVariable id: Long) {
    }

}