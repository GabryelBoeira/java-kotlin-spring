package com.gabryel.fundamentos

fun main() {
    //Lista Imutavel
    var listaInteiros = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    var listaStrings = listOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j")

    //utilizando filter, retorna uma nova lista
    println("Lista Imutavel: " + listaStrings.filter { s: String ->  s == "a" })
    println("Lista Imutavel: " + listaInteiros.filter { i: Int ->  i % 2 == 0 })

    //lista mutavel
    var listaInteirosMutavel = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    listaInteirosMutavel.add(11)
    listaInteirosMutavel.remove(3)
    println("\nLista mutavel: $listaInteirosMutavel")

    listaInteirosMutavel.shuffle()
    println("shuffle: $listaInteirosMutavel")
    listaInteirosMutavel.sort()
    println("sort: $listaInteirosMutavel")

    //set nao permite itens duplicados
    var listaStringsMutavel = mutableSetOf("a", "b", "c", "d", "e", "f", "g", "h", "i", "j","j")
    println("\nSet: $listaStringsMutavel")
    println("Set.contains: ${listaStringsMutavel.contains("a")}")

    var listaMap = mapOf("a" to 1, "b" to 2, "c" to 3, "d" to 4, "e" to 5, "f" to 6, "g" to 7, "h" to 8, "i" to 9, "j" to 10)
    println("\nMap: $listaMap")
    println("Map.get : ${listaMap.get("a")}")
}