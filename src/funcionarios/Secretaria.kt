package funcionarios

class Secretaria(
    id: Int,
    nome: String,
    telefone: String,
    salario: Double,
    private var turno: String
) : Funcionario(id, nome, "Secretária", telefone, salario) {

    fun getTurno() = turno
    fun setTurno(t: String) { turno = t }

    override fun exibirInformacoes() {
        super.exibirInformacoes()
        println("Turno de trabalho: $turno")
        println("===============================")
    }
}
