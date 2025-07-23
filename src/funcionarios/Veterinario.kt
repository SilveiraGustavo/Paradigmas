package funcionarios

class Veterinario(
    id: Int,
    nome: String,
    telefone: String,
    salario: Double,
    private var crmv: String
) : Funcionario(id, nome, "Veterinário", telefone, salario) {

    fun getCrmv() = crmv
    fun setCrmv(c: String) { crmv = c }

    override fun exibirInformacoes() {
        super.exibirInformacoes()
        println("CRMV: $crmv")
        println("===============================")
    }
}
