package cliente

class Cliente(
    nome: String,
    telefone: String,
    endereco: String,
    private var cpf: String
) : Pessoa(nome, telefone, endereco) {

    fun getCpf() = cpf
    fun setCpf(c: String) { cpf = c }

    override fun exibirInformacoes() {
        println("===== Dados do Cliente =====")
        super.exibirInformacoes()
        println("CPF: $cpf")
        println("============================")
    }
}
