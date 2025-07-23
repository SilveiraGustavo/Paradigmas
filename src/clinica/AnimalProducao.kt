package clinica

import cliente.Pessoa

class AnimalProducao(
    id: Int,
    nome: String,
    idade: Int,
    tutor: Pessoa,
    especie: String,
    private var tipoProducao: String, // ex: leite, ovos, lã
    private var quantidadeProducao: Double // litros, unidades, etc.
) : Animal(id, nome, especie, idade, tutor) {

    fun getTipoProducao() = tipoProducao
    fun setTipoProducao(tp: String) {
        tipoProducao = tp
    }

    fun getQuantidadeProducao() = quantidadeProducao
    fun setQuantidadeProducao(qp: Double) {
        quantidadeProducao = qp
    }

    override fun exibirInformacoes() {
        println("===== Dados do Animal de Produção =====")
        println("ID: $id")
        println("Nome: $nome")
        println("Idade: $idade anos")
        println("Espécie: $especie")
        println("Tutor: ${tutor.nome}")
        println("Tipo de Produção: $tipoProducao")
        println("Quantidade Produzida: $quantidadeProducao")
        println("=======================================")
    }
}
