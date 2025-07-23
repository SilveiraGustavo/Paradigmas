package clinica  // Sugestão: pacote em minúsculas

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Remedio(
    private var id: Int,
    private var nome: String,
    private var principioAtivo: String,
    private var fabricante: String,
    private var lote: String,
    private var validade: String, // formato "yyyy-MM-dd"
    private var quantidadeEstoque: Int,
    private var viaAdministracao: String,
    private var precoUnitario: Double,
    private var restricaoUso: String? = null
) {
    // Getters
    fun getId() = id
    fun getNome() = nome
    fun getPrincipioAtivo() = principioAtivo
    fun getFabricante() = fabricante
    fun getLote() = lote
    fun getValidade() = validade
    fun getQuantidadeEstoque() = quantidadeEstoque
    fun getViaAdministracao() = viaAdministracao
    fun getPrecoUnitario() = precoUnitario
    fun getRestricaoUso() = restricaoUso

    // Setter para quantidade de estoque
    fun setQuantidadeEstoque(qtd: Int) {
        if (qtd >= 0) {
            quantidadeEstoque = qtd
        }
    }

    // Repor estoque
    fun reporEstoque(qtd: Int) {
        if (qtd > 0) {
            quantidadeEstoque += qtd
        }
    }

    // Consumir estoque se houver quantidade suficiente
    fun consumirEstoque(qtd: Int): Boolean {
        return if (quantidadeEstoque >= qtd && qtd > 0) {
            quantidadeEstoque -= qtd
            true
        } else {
            false
        }
    }

    // Verificar se o remédio está vencido
    fun estaVencido(): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dataValidade = LocalDate.parse(validade, formatter)
        return dataValidade.isBefore(LocalDate.now())
    }

    // Exibir informações do remédio
    fun exibirInformacoes() {
        println("===== Informações do Remédio =====")
        println("ID: $id")
        println("Nome: $nome")
        println("Princípio ativo: $principioAtivo")
        println("Fabricante: $fabricante")
        println("Lote: $lote")
        println("Validade: $validade")
        println("Quantidade em estoque: $quantidadeEstoque")
        println("Via de administração: $viaAdministracao")
        println("Preço unitário: R$ $precoUnitario")
        println("Restrições de uso: ${restricaoUso ?: "Nenhuma"}")
        println("Está vencido? ${if (estaVencido()) "Sim" else "Não"}")
        println("=================================")
    }
}
