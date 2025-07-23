package clinica

import cliente.Pessoa

class AnimalPesquisa(
    id: Int,
    nome: String,
    idade: Int,
    tutor: Pessoa,
    especie: String,
    private var numeroLaboratorio: String,
    private var protocoloEstudo: String,
    private var finalidade: String
) : Animal(id, nome, especie, idade, tutor) {

    fun getNumeroLaboratorio() = numeroLaboratorio
    fun setNumeroLaboratorio(nl: String) {
        numeroLaboratorio = nl
    }

    fun getProtocoloEstudo() = protocoloEstudo
    fun setProtocoloEstudo(pe: String) {
        protocoloEstudo = pe
    }

    fun getFinalidade() = finalidade
    fun setFinalidade(f: String) {
        finalidade = f
    }

    override fun exibirInformacoes() {
        println("===== Dados do Animal de Pesquisa =====")
        println("ID: $id")
        println("Nome: $nome")
        println("Idade: $idade anos")
        println("Espécie: $especie")
        println("Tutor: ${tutor.nome}")
        println("Número do Laboratório: $numeroLaboratorio")
        println("Protocolo do Estudo: $protocoloEstudo")
        println("Finalidade: $finalidade")
        println("=======================================")
    }
}
