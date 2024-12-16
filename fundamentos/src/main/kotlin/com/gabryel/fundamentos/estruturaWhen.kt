package com.gabryel.fundamentos

fun main() {

    val nota = 9.7
    when (nota) {
        9.0, 10.0 -> println("Excelente")
        8.0, 7.0 -> println("Bom")
        6.0, 5.0 -> println("Ruim")
        else -> println("Reprovado")
    }

    val idade = 20
    when (idade) {
        in 0..15 -> println("Criança")
        in 16..30 -> println("Jovem")
        in 31..60 -> println("Adulto")
        in 61..100 -> println("Idoso")
        else -> println("centenario")
    }
}