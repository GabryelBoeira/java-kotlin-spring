package com.gabryel.fundamentos

class CompanionObject (
    var nome: String,
    var idade: Int,
    var endereco: String
) {
    companion object {
        fun criarPorPadrao(): CompanionObject = CompanionObject("teste", 20, "rua 1 ")
    }
}

class SomeClass(
    var nome: String,
    var idade: Int,
    var endereco: String
) {
    fun criarPorPadrao(): SomeClass {
        return SomeClass("teste 2 ", 10, "rua 2")
    }
}

fun main() {
    //Nao precisa instanciar classe
    var companionObject = CompanionObject.criarPorPadrao()
    println("companionObject " + companionObject.endereco)

    //Classe normal que precisa instanciar
    var someClass = SomeClass("", 0, "").criarPorPadrao()
    println("someClass: "+ someClass.endereco)
}