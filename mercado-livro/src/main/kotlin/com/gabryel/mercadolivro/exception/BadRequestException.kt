package com.gabryel.mercadolivro.exception

class BadRequestException(val code: String, override val message: String) : Exception() {
}