package com.gabryel.fundamentos;

//Funcao que retorna void
fun main() {
    println(getString())
    println(soma(2, 3))
    println(getDados("Thor", 20))
    println(getDados(idade = 25, nome = "Thor"))
    println(getDados("Thor")) //Idade padrao()
}

//Funcao que retorna uma String
fun getString(): String {
    return "Hello World"
}

//Funcao com retorno e recebendo paramentro a e b
fun soma(a: Int, b: Int): Int {
    return a + b
}

//Concaternando String
fun getDados(nome: String, idade: Int = 0): String {
    return "Nome: $nome, Idade: $idade"
}