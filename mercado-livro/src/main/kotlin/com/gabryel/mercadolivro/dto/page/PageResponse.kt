package com.gabryel.mercadolivro.dto.page

data class PageResponse<T> (
    val items: List<T>,
    val page: Int,
    val totalPages: Int,
    val totalElements: Long,
    val size: Int,
)