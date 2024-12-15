package fundamentos

class Carro(var cor: String, val marca: String, var modelo: String, var dono: Dono) {
}

class Dono(val nome: String, var idade: Int)

fun main() {
    val carro = Carro("Vermelho", "Fiat", "Uno", Dono("Gabriel", 20))
    println(carro.cor)
    println(carro.marca)
    println(carro.modelo)
    println(carro.dono.nome)
    println(carro.dono.idade)


}
