package clinica
// Sugiro deixar o nome do pacote todo minúsculo para seguir padrão Kotlin

import clinica.Animal
import cliente.Pessoa

class AnimalCarga(
    id: Int,
    nome: String,
    idade: Int,
    tutor: Pessoa,
    especie: String,
    private var capacidade: Double,
    private var tipoAlimentacao: String,
    private var tipoCarga: String,
    private var localTrabalho: String
) : Animal(id, nome, especie, idade, tutor) {

    // Getters e setters
    fun getCapacidade() = capacidade
    fun setCapacidade(c: Double) {
        capacidade = c
    }

    fun getTipoAlimentacao() = tipoAlimentacao
    fun setTipoAlimentacao(ta: String) {
        tipoAlimentacao = ta
    }

    fun getTipoCarga() = tipoCarga
    fun setTipoCarga(tc: String) {
        tipoCarga = tc
    }

    fun getLocalTrabalho() = localTrabalho
    fun setLocalTrabalho(lt: String) {
        localTrabalho = lt
    }

    // Sobrescreve o método da superclasse para exibir informações específicas
    override fun exibirInformacoes() {
        println("===== Dados do Animal de Carga =====")
        println("ID: $id")
        println("Nome: $nome")
        println("Idade: $idade anos")
        println("Espécie: $especie")
        println("Tutor: ${tutor.nome}")
        println("Capacidade de Carga: $capacidade kg")
        println("Tipo de Alimentação: $tipoAlimentacao")
        println("Tipo de Carga: $tipoCarga")
        println("Local de Trabalho: $localTrabalho")
        println("====================================")
    }
}
