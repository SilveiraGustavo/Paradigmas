package cliente

open class Pessoa(
    val nome: String,
    val telefone: String,
    val endereco: String
) {
    open fun exibirInformacoes() {
        println("===== Informações da Pessoa =====")
        println("Nome: $nome")
        println("Telefone: $telefone")
        println("Endereço: $endereco")
        println("=================================")
    }
}