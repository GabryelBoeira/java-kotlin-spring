package com.gabryel.fundamentos

fun retornaNumeroPorExtenso(numero: Int): String? {
    return when (numero) {
        1 -> "Um"
        2 -> "Dois"
        3 -> "Tres"
        else -> null
    }
}

fun main() {
    println(retornaNumeroPorExtenso(1))
    println(retornaNumeroPorExtenso(2))
    println(retornaNumeroPorExtenso(3))
    println(retornaNumeroPorExtenso(6))
}