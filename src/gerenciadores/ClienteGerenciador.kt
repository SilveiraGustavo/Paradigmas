package gerenciadores

import cliente.Pessoa

class ClienteGerenciador {

    private val clientes = mutableListOf<Pessoa>()

    // Adiciona um cliente
    fun adicionarCliente(cliente: Pessoa) {
        clientes.add(cliente)
    }

    // Lista todos os clientes
    fun listarClientes() {
        if (clientes.isEmpty()) {
            println("Nenhum cliente cadastrado.")
        } else {
            println("=== Lista de Clientes ===")
            clientes.forEach { it.exibirInformacoes() }
        }
    }

    // Busca clientes pelo nome (busca parcial, case insensitive)
    fun buscarClientePorNome(nome: String): List<Pessoa> {
        return clientes.filter { it.nome.contains(nome, ignoreCase = true) }
    }

    // Remove clientes pelo nome exato (case insensitive)
    // Retorna true se removeu pelo menos 1, false se não encontrou ninguém
    fun removerClientePorNome(nome: String): Boolean {
        val removidos = clientes.removeAll { it.nome.equals(nome, ignoreCase = true) }
        return removidos
    }

    // Quantidade total de clientes
    fun totalClientes(): Int {
        return clientes.size
    }
}
