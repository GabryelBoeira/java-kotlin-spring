package com.gabryel.mercadolivro.repository

import com.gabryel.mercadolivro.model.PurchaseModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface PurchaseRepository : CrudRepository<PurchaseModel, Long> {

}