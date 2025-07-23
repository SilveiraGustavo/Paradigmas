package clinica

import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Consulta(
    val id: Int,
    val animal: Animal,  // objeto Animal associado
    var data: LocalDate, // pode ser alterada
    var motivo: String,  // pode ser alterado
    var veterinario: String // pode ser alterado
) {
    private var realizada: Boolean = false

    fun isRealizada() = realizada

    fun setRealizada(status: Boolean) {
        realizada = status
    }

    fun imprimirResumo() {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        println("Consulta ID: $id")
        println("Animal ID: ${animal.obterId()}")
        println("Data: ${data.format(formatter)}")
        println("Motivo: $motivo")
        println("Veterinário: $veterinario")
        println("Status: ${if (realizada) "Realizada" else "Pendente"}")
        println("---------------------------")
    }
}
