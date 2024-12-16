package fundamentos

fun main() {
    parOuImpar(10)
    parOuImpar(11)

    alunoAprovado(6.9)
    alunoAprovado(8.9)
    alunoAprovado(4.9)
}

fun parOuImpar(numero: Int) {
    if (numero % 2 == 0) {
        println("Par")
    } else {
        println("Impar")
    }
}

fun alunoAprovado(nota: Double) {
    if (nota >= 7) {
        println("Aprovado")
    } else if (nota >= 5) {
        println("Recuperação")
    } else {
        println("Reprovado")
    }
}