package clinica

import cliente.Pessoa

open class Animal(
    protected var id: Int,
    var nome: String,
    protected var especie: String,
    protected var idade: Int,
    protected var tutor: Pessoa
) {
    // Se quiser expor o id publicamente, crie um getter com nome diferente para evitar conflito
    fun obterId() = id

    // Getters (opcionais, já pode usar propriedades diretamente)
    fun obterNome() = nome
    fun obterEspecie() = especie
    fun obterIdade() = idade
    fun obterTutor() = tutor

    // Setters


    open fun exibirInformacoes() {
        println("===== Dados do Animal =====")
        println("ID: $id")
        println("Nome: $nome")
        println("Espécie: $especie")
        println("Idade: $idade")
        println("Tutor: ${tutor.nome}") // Usando getter para acessar nome do tutor
        println("===========================")
    }
}