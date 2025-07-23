package gerenciadores

import funcionarios.Funcionario

class FuncionarioGerenciador {
    private val funcionarios = mutableListOf<Funcionario>()

    fun adicionarFuncionario(funcionario: Funcionario) {
        funcionarios.add(funcionario)
    }

    fun listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            println("Nenhum funcionário cadastrado.")
        } else {
            funcionarios.forEach { it.exibirInformacoes() }
        }
    }

    fun buscarFuncionarioPorNome(nome: String): List<Funcionario> {
        return funcionarios.filter { it.getNome().contains(nome, ignoreCase = true) }
    }

    fun removerFuncionarioPorId(id: Int): Boolean {
        return funcionarios.removeIf { it.getId() == id }
    }
}
