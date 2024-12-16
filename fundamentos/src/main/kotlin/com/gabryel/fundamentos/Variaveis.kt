package com.gabryel.fundamentos

class Variaveis {
    fun inicializa(): Int {
        var a: Int = 10
        var b: Int = 20
        return a + b
    }
}

fun main() {
    val a: Int = 10
    var b: Int
    b = 20

    println(a)
    println(b)
    var params = Variaveis().inicializa()
    println(params)
}