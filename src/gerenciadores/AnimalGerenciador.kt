package gerenciadores

import clinica.Animal

class AnimalGerenciador {
    private val animais = mutableListOf<Animal>()

    fun adicionarAnimal(animal: Animal) {
        animais.add(animal)
    }

    fun listarAnimais() {
        if (animais.isEmpty()) {
            println("Nenhum animal cadastrado.")
        } else {
            animais.forEach { it.exibirInformacoes() }
        }
    }

    fun buscarAnimalPorNome(nome: String): Animal? {
        return animais.find { it.obterNome().equals(nome, ignoreCase = true) }
    }

    fun removerAnimalPorNome(nome: String): Boolean {
        return animais.removeIf { it.obterNome().equals(nome, ignoreCase = true) }
    }
}
