package clinica

import cliente.Pessoa

class AnimalDomestico(
    id: Int,
    nome: String,
    especie: String,
    idade: Int,
    tutor: Pessoa,
    private var raca: String,
    private var alimentoPreferido: String
) : Animal(id, nome, especie, idade, tutor) {

    override fun exibirInformacoes() {
        super.exibirInformacoes()
        println("Raça: $raca")
        println("Alimento Preferido: $alimentoPreferido")
    }
}
