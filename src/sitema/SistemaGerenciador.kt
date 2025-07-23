package sistema

import cliente.Cliente
import clinica.*
import funcionarios.Funcionario

class SistemaGerenciador {

    private val clientes = mutableListOf<Cliente>()
    private val funcionarios = mutableListOf<Funcionario>()
    private val animais = mutableListOf<Animal>()
    private val consultas = mutableListOf<Consulta>()
    private val remedios = mutableListOf<Remedio>()

    // Busca retorna lista, pois pode haver mais de um com mesmo nome
    fun buscarClientePorNome(nome: String): List<Cliente> {
        return clientes.filter { it.nome.contains(nome, ignoreCase = true) }
    }

    fun buscarAnimalPorNome(nome: String): List<Animal> {
        return animais.filter { it.obterNome().equals(nome, ignoreCase = true) }
    }

    fun buscarFuncionarioPorNome(nome: String): List<Funcionario> {
        return funcionarios.filter { it.getNome().equals(nome, ignoreCase = true) }
    }

    // ----- CLIENTES -----
    fun cadastrarCliente(cliente: Cliente) {
        clientes.add(cliente)
    }

    fun listarClientes() {
        if (clientes.isEmpty()) {
            println("Nenhum cliente cadastrado.")
        } else {
            clientes.forEach { it.exibirInformacoes() }
        }
    }

    fun removerClientePorNome(nome: String): Boolean {
        val cliente = clientes.find { it.nome.equals(nome, ignoreCase = true) }
        return if (cliente != null) {
            clientes.remove(cliente)
            true
        } else {
            false
        }
    }

    // ----- ANIMAIS -----
    fun cadastrarAnimal(animal: Animal) {
        animais.add(animal)
    }

    fun listarAnimais() {
        if (animais.isEmpty()) {
            println("Nenhum animal cadastrado.")
        } else {
            animais.forEach { it.exibirInformacoes() }
        }
    }

    fun removerAnimalPorNome(nome: String): Boolean {
        val animal = animais.find { it.obterNome().equals(nome, ignoreCase = true) }
        return if (animal != null) {
            animais.remove(animal)
            true
        } else {
            false
        }
    }

    // ----- FUNCIONÁRIOS -----
    fun cadastrarFuncionario(funcionario: Funcionario) {
        funcionarios.add(funcionario)
    }

    fun listarFuncionarios() {
        if (funcionarios.isEmpty()) {
            println("Nenhum funcionário cadastrado.")
        } else {
            funcionarios.forEach { it.exibirInformacoes() }
        }
    }

    fun removerFuncionarioPorId(id: Int): Boolean {
        val funcionario = funcionarios.find { it.getId() == id }
        return if (funcionario != null) {
            funcionarios.remove(funcionario)
            true
        } else {
            false
        }
    }

    // ----- CONSULTAS -----
    fun cadastrarConsulta(consulta: Consulta) {
        consultas.add(consulta)
    }

    fun listarConsultas() {
        if (consultas.isEmpty()) {
            println("Nenhuma consulta cadastrada.")
        } else {
            consultas.forEach { it.imprimirResumo() }
        }
    }

    fun buscarConsultaPorId(id: Int): Consulta? {
        return consultas.find { it.id == id }
    }

    // ----- REMÉDIOS -----
    fun cadastrarRemedio(remedio: Remedio) {
        remedios.add(remedio)
    }

    fun listarRemedios() {
        if (remedios.isEmpty()) {
            println("Nenhum remédio cadastrado.")
        } else {
            remedios.forEach { it.exibirInformacoes() }
        }
    }
}
