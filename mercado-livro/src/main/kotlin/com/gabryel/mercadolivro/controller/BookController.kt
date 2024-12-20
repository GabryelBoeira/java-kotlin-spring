package com.gabryel.mercadolivro.controller

import com.gabryel.mercadolivro.dto.book.BookDetailDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

@RestController
@RequestMapping("/books")
class BookController {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<BookDetailDTO> {
        return listOf()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getOne(): BookDetailDTO {
        return BookDetailDTO(1, "Livro", BigDecimal(10.0))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun save() {

    }


}