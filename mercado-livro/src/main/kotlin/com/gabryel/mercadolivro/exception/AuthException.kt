package com.gabryel.mercadolivro.exception

class AuthException(override val message: String, val internalCode: String)  : Exception() {
}