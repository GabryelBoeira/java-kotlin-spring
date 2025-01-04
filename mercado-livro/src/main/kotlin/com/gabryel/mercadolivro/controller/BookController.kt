package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.book.BookDetailDTO
import com.gabryel.mercadolivro.dto.book.BookSaveDTO
import com.gabryel.mercadolivro.dto.book.BookUpdateDTO
import com.gabryel.mercadolivro.dto.page.PageResponse
import com.gabryel.mercadolivro.enums.BookStatus
import com.gabryel.mercadolivro.secutity.UserCanOnlyAccessTheirOwnResource
import com.gabryel.mercadolivro.service.BookService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController (
  private val bookService: BookService,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    fun getAll(@PageableDefault(page = 0, size = 10) pageable: Pageable, @RequestParam name: String?): PageResponse<BookDetailDTO> {
        return bookService.getAll(pageable, name)
    }

    @GetMapping("/active")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all active books")
    fun getByStatusActive(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookDetailDTO> {
        return bookService.getByStatusActive(pageable)
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
    @UserCanOnlyAccessTheirOwnResource
    fun update(@PathVariable id: Long, @Valid @RequestBody book: BookUpdateDTO) {
        bookService.update(id,book)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) {
        bookService.delete(id)
    }

    @PatchMapping("/{id}/status/{status}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateStatus(@PathVariable id: Long, @PathVariable status: BookStatus) {
        bookService.patchStatus(id, status)
    }

}