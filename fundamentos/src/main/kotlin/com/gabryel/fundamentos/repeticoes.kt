package com.gabryel.fundamentos

fun main() {

    // For de 1 a 10
    for (i in 1..10) {
        println("For: $i")
    }

    // For in reverso
    for (i in 10 downTo 1) {
        println("Reverso: $i")
    }

    // For de 1 a 10 de 2 em 2
    for (i in 1..10 step 2) {
        println("Step 2: $i")
    }

    // For que percore um intervalo de 1 a menor que 10
    for (i in 1 until 10) {
        println("Until menor que 10: $i")
    }

    // For de a a z mesma ideia do for de 1 a 10
    for (i in 'a'..'g') {
        println(i)
    }

    var i = 0
    while (i < 10) {
        println("While: $i")
        i++
    }

    i = 0
    do {
        println("Do While: $i")
        i++
    } while (i < 10)
}