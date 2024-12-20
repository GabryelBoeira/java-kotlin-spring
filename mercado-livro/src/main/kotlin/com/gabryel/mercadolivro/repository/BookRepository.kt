package com.gabryel.mercadolivro.repository

import com.gabryel.mercadolivro.model.BookModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository : CrudRepository<BookModel, Long> {
}