package com.gabryel.mercadolivro.repository

import com.gabryel.mercadolivro.enums.BookStatus
import com.gabryel.mercadolivro.model.BookModel
import com.gabryel.mercadolivro.model.PurchaseModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository : CrudRepository<PurchaseModel, Long> {

    /**
     * Finds all purchases with pagination support.
     *
     * @param pageable the pagination information.
     * @return a paginated list of all [BookModel]s.
     */
    fun findAll(pageable: Pageable): Page<PurchaseModel>



}