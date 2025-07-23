package clinica

import cliente.Pessoa
import gerenciadores.ClienteGerenciador
import gerenciadores.AnimalGerenciador
import gerenciadores.ConsultaGerenciador

class Clinica {

    private val clientes = mutableListOf<Pessoa>()
    private val animais = mutableListOf<Animal>()
    private val consultas = mutableListOf<Consulta>()
    private val remedios = mutableListOf<Remedio>()

    fun imprimirResumo() {
        println("===== Resumo da Clínica =====")
        println("Total de Clientes: ${clientes.size}")
        println("Total de Animais: ${animais.size}")
        println("Total de Consultas: ${consultas.size}")
        println("=============================")
    }

    // Clientes
    fun cadastrarCliente(cliente: Pessoa) {
        clientes.add(cliente)
    }

    fun listarClientes() {
        println("=== Lista de Clientes ===")
        clientes.forEach { it.exibirInformacoes() }
    }

    fun buscarClientePorNome(nome: String): Pessoa? {
        return clientes.find { it.nome.equals(nome, ignoreCase = true) }
    }

    // Animais
    fun cadastrarAnimal(animal: Animal) {
        animais.add(animal)
    }

    fun listarAnimais() {
        println("=== Lista de Animais ===")
        animais.forEach { it.exibirInformacoes() }
    }

    fun buscarAnimalPorNome(nome: String): Animal? {
        return animais.find { it.nome.equals(nome, ignoreCase = true) }
    }
    // Consultas
    fun cadastrarConsulta(consulta: Consulta) {
        consultas.add(consulta)
    }

    fun listarConsultas() {
        println("=== Lista de Consultas ===")
        consultas.forEach { it.imprimirResumo() }
    }

    // Remédios
    fun cadastrarRemedio(remedio: Remedio) {
        remedios.add(remedio)
    }

    fun listarRemedios() {
        println("=== Lista de Remédios ===")
        remedios.forEach { it.exibirInformacoes() }
    }
}
