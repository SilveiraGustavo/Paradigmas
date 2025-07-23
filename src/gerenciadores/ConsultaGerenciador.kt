package gerenciadores

import clinica.Consulta

class ConsultaGerenciador {
    private val consultas = mutableListOf<Consulta>()

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
}