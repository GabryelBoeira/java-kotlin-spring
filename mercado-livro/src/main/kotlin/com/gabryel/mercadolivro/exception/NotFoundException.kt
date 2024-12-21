package com.gabryel.mercadolivro.exception

class NotFoundException(override val message: String, val internalCode: String)  : Exception() {
}