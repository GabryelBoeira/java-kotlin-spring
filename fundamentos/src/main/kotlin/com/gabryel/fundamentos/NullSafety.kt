package com.gabryel.fundamentos

import kotlin.random.Random

fun main() {

    var nome: String? = null
    println(nome?.length)

    var idade: Int? = null
    println(idade)

    nome = getStringValue()
    val result = if (nome?.length!! <= 5 ) 0 else 1
    println(result)

    var pessoa: Dono? = Dono("Gabriel", 20)
    println("Idade do dono: " + pessoa?.idade)

}

fun getStringValue(): String? {
    val intValue = Random.nextInt(-10, 10)
    var returnString: String? = ""
    for (i in 1..intValue) {
        returnString+= "a"
    }
    return returnString
}

