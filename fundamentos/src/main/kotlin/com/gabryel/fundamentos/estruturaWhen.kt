package com.gabryel.fundamentos

fun main() {
    val nota = 9.7
    when (nota) {
        9.0, 10.0 -> println("Excelente")
        8.0, 7.0 -> println("Bom")
        6.0, 5.0 -> println("Ruim")
        else -> println("Reprovado")
    }
}