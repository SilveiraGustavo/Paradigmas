package sitema

import funcionarios.*
import cliente.*
import clinica.*
import gerenciadores.AnimalGerenciador
import gerenciadores.ClienteGerenciador
import gerenciadores.ConsultaGerenciador
import gerenciadores.FuncionarioGerenciador
import java.awt.GridLayout
import java.time.LocalDate
import java.time.format.DateTimeParseException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.awt.*
import java.awt.event.ActionEvent
import javax.swing.*

fun main() {
    val url = "jdbc:mysql://localhost/clinicavet"
    val user = "root"
    val password = ""
    val conexao = criarConexao(url, user, password)!!
    val gerenciadorFuncionarios = FuncionarioGerenciador()
    val gerenciadorClientes = ClienteGerenciador()
    val gerenciadorAnimais = AnimalGerenciador()
    val gerenciadorConsultas = ConsultaGerenciador()

    SwingUtilities.invokeLater {
        criarJanelaPrincipal(
            gerenciadorFuncionarios,
            gerenciadorClientes,
            gerenciadorAnimais,
            gerenciadorConsultas,
            conexao
        )
    }


    /*while (true) {
        println("=== Sistema Clínica Veterinária ===")
        println("1. Gerenciar Funcionários")
        println("2. Gerenciar Clientes")
        println("3. Gerenciar Animais")
        println("4. Gerenciar Consultas")
        println("0. Sair")
        print("Escolha uma opção: ")
        when (readLine()?.trim()) {
            "1" -> menuFuncionarios(gerenciadorFuncionarios)
            "2" -> menuClientes(gerenciadorClientes, conexao)
            "3" -> menuAnimais(gerenciadorAnimais, gerenciadorClientes)
            "4" -> menuConsultas(gerenciadorConsultas, gerenciadorAnimais)
            "0" -> {
                println("Saindo... Até mais!")
                return
            }
            else -> println("Opção inválida, tente novamente.")
        }
        println()
    }*/

}

fun criarJanelaPrincipal(
    gerenciadorFuncionarios: FuncionarioGerenciador,
    gerenciadorClientes: ClienteGerenciador,
    gerenciadorAnimais: AnimalGerenciador,
    gerenciadorConsultas: ConsultaGerenciador,
    conexao: Connection
) {
    val frame = JFrame("Sistema Clínica Veterinária")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.setSize(400, 300)
    frame.layout = GridLayout(5, 1)

    val btnFuncionarios = JButton("Gerenciar Funcionários")
    val btnClientes = JButton("Gerenciar Clientes")
    val btnAnimais = JButton("Gerenciar Animais")
    val btnConsultas = JButton("Gerenciar Consultas")
    val btnSair = JButton("Sair")

    btnFuncionarios.addActionListener {
        menuFuncionarios(gerenciadorFuncionarios)
    }

    btnClientes.addActionListener {
        menuClientesGUI(gerenciadorClientes, conexao)
    }

    btnAnimais.addActionListener {
        menuAnimais(gerenciadorAnimais, gerenciadorClientes)
    }

    btnConsultas.addActionListener {
        menuConsultas(gerenciadorConsultas, gerenciadorAnimais)
    }

    btnSair.addActionListener {
        JOptionPane.showMessageDialog(frame, "Saindo... Até mais!")
        frame.dispose()
    }

    frame.add(btnFuncionarios)
    frame.add(btnClientes)
    frame.add(btnAnimais)
    frame.add(btnConsultas)
    frame.add(btnSair)

    frame.setLocationRelativeTo(null)
    frame.isVisible = true
}

fun criarConexao(url: String, user: String, password: String): Connection? {
    return try {
        DriverManager.getConnection(url, user, password)
    } catch (e: SQLException) {
        println("Erro ao conectar ao banco: ${e.message}")
        null
    }
}

// --- Menu Funcionários ---

fun menuFuncionarios(gerenciador: FuncionarioGerenciador) {
    while (true) {
        println("\n--- Menu Funcionários ---")
        println("1. Cadastrar Funcionário")
        println("2. Listar Funcionários")
        println("3. Buscar Funcionário por Nome")
        println("4. Remover Funcionário por ID")
        println("0. Voltar")
        print("Opção: ")
        when (readLine()?.trim()) {
            "1" -> {
                print("Digite o tipo (1-Veterinário, 2-Secretária): ")
                val tipo = readLine()?.trim()
                print("ID: ")
                val id = readLine()?.toIntOrNull() ?: continue
                print("Nome: ")
                val nome = readLine() ?: continue
                print("Telefone: ")
                val telefone = readLine() ?: continue
                print("Salário: ")
                val salario = readLine()?.toDoubleOrNull() ?: continue

                when (tipo) {
                    "1" -> {
                        print("CRMV: ")
                        val crmv = readLine() ?: continue
                        val vet = Veterinario(id, nome, telefone, salario, crmv)
                        gerenciador.adicionarFuncionario(vet)
                        println("Veterinário cadastrado com sucesso.")
                    }
                    "2" -> {
                        print("Turno: ")
                        val turno = readLine() ?: continue
                        val sec = Secretaria(id, nome, telefone, salario, turno)
                        gerenciador.adicionarFuncionario(sec)
                        println("Secretária cadastrada com sucesso.")
                    }
                    else -> println("Tipo inválido.")
                }
            }
            "2" -> gerenciador.listarFuncionarios()
            "3" -> {
                print("Nome para busca: ")
                val nomeBusca = readLine() ?: continue
                val resultados = gerenciador.buscarFuncionarioPorNome(nomeBusca)
                if (resultados.isEmpty()) println("Nenhum funcionário encontrado.")
                else resultados.forEach { it.exibirInformacoes() }
            }
            "4" -> {
                print("ID para remover: ")
                val idRemover = readLine()?.toIntOrNull()
                if (idRemover == null) {
                    println("ID inválido.")
                } else {
                    if (gerenciador.removerFuncionarioPorId(idRemover)) {
                        println("Funcionário removido.")
                    } else {
                        println("Funcionário não encontrado.")
                    }
                }
            }
            "0" -> return
            else -> println("Opção inválida.")
        }
    }
}

// --- Menu Clientes ---

fun menuClientes(gerenciador: ClienteGerenciador, conexao: Connection) {
    while (true) {
        println("\n--- Menu Clientes ---")
        println("1. Cadastrar Cliente")
        println("2. Listar Clientes")
        println("3. Buscar Cliente por Nome")
        println("4. Remover Cliente por Nome")
        println("0. Voltar")
        print("Opção: ")
        when (readLine()?.trim()) {
            "1" -> {
                print("Nome: ")
                val nome = readLine() ?: continue
                print("Telefone: ")
                val telefone = readLine() ?: continue
                print("Endereço: ")
                val endereco = readLine() ?: continue
                print("CPF: ")
                val cpf = readLine() ?: continue

                val cliente = Cliente(nome, telefone, endereco, cpf)
                gerenciador.adicionarCliente(cliente)
                gerenciador.salvarClienteNoBanco(cliente, conexao)
                println("Cliente cadastrado com sucesso.")
            }
            "2" -> {
                    gerenciador.listarClientes()
                    gerenciador.listarClientesBD(conexao)
            }
            "3" -> {
                print("Nome para busca: ")
                val nomeBusca = readLine() ?: continue
                val resultados = gerenciador.buscarClientePorNome(nomeBusca)
                if (resultados.isEmpty()) println("Nenhum cliente encontrado.")
                else resultados.forEach { it.exibirInformacoes() }
                gerenciador.buscarClientePorNomeBD(conexao,nomeBusca)

            }
            "4" -> {
                print("Nome para remover: ")
                val nomeRemover = readLine() ?: continue
                print("ID para remover: ")
                val idRemoverStr = readLine()
                val idRemover = idRemoverStr?.toIntOrNull()

                if (idRemover != null) {
                    gerenciador.removerClientePorIdBD(conexao, idRemover) // aqui é Int não nulo
                } else {
                    println("ID inválido")
                }
                if (gerenciador.removerClientePorNome(nomeRemover)) {
                    println("Cliente removido.")
                } else {
                    println("Cliente não encontrado.")
                }
            }
            "0" -> return
            else -> println("Opção inválida.")
        }
    }
}

// --- Menu Animais ---

fun menuAnimais(gerenciador: AnimalGerenciador, gerenciadorClientes: ClienteGerenciador) {
    while (true) {
        println("\n--- Menu Animais ---")
        println("1. Cadastrar Animal Doméstico")
        println("2. Listar Animais")
        println("3. Buscar Animal por Nome")
        println("4. Remover Animal por Nome")
        println("0. Voltar")
        print("Opção: ")
        when (readLine()?.trim()) {
            "1" -> {
                print("ID: ")
                val id = readLine()?.toIntOrNull() ?: continue
                print("Nome: ")
                val nome = readLine() ?: continue
                print("Espécie: ")
                val especie = readLine() ?: continue
                print("Idade: ")
                val idade = readLine()?.toIntOrNull() ?: continue
                print("Nome do tutor: ")
                val nomeTutor = readLine() ?: continue

                val tutor = gerenciadorClientes.buscarClientePorNome(nomeTutor).firstOrNull()
                if (tutor == null) {
                    println("Tutor não encontrado. Cadastre o cliente primeiro.")
                } else {
                    // Criando animal doméstico com alguns dados fixos para exemplo
                    val animal = AnimalDomestico(
                        id, nome, especie, idade, tutor,
                        "Sem raça definida", "Ração"
                    )
                    gerenciador.adicionarAnimal(animal)
                    println("Animal cadastrado com sucesso.")
                }
            }
            "2" -> gerenciador.listarAnimais()
            "3" -> {
                print("Nome para busca: ")
                val nomeBusca = readLine() ?: continue
                val resultados = gerenciador.buscarAnimalPorNome(nomeBusca)
                if (resultados == null) {
                    println("Nenhum animal encontrado.")
                } else {
                    resultados.exibirInformacoes()
                }
            }
            "4" -> {
                print("Nome para remover: ")
                val nomeRemover = readLine() ?: continue
                if (gerenciador.removerAnimalPorNome(nomeRemover)) {
                    println("Animal removido.")
                } else {
                    println("Animal não encontrado.")
                }
            }
            "0" -> return
            else -> println("Opção inválida.")
        }
    }
}

// --- Menu Consultas ---

fun menuConsultas(gerenciador: ConsultaGerenciador, gerenciadorAnimais: AnimalGerenciador) {
    while (true) {
        println("\n--- Menu Consultas ---")
        println("1. Cadastrar Consulta")
        println("2. Listar Consultas")
        println("3. Buscar Consulta por ID")
        println("4. Marcar Consulta como Realizada")
        println("0. Voltar")
        print("Opção: ")
        when (readLine()?.trim()) {
            "1" -> {
                print("ID: ")
                val id = readLine()?.toIntOrNull() ?: continue

                print("Nome do animal: ")
                val nomeAnimal = readLine() ?: continue

                val animal = gerenciadorAnimais.buscarAnimalPorNome(nomeAnimal)
                if (animal == null) {
                    println("Animal não encontrado. Cadastre o animal primeiro.")
                } else {
                    print("Data da consulta (aaaa-mm-dd): ")
                    val dataStr = readLine() ?: continue

                    val data = try {
                        LocalDate.parse(dataStr)
                    } catch (e: DateTimeParseException) {
                        println("Data inválida: ${e.message}. Use o formato aaaa-mm-dd.")
                        continue
                    }

                    print("Digite o nome do veterinário responsável: ")
                    val vet = readLine() ?: ""

                    print("Digite o motivo da consulta: ")
                    val motivo = readLine() ?: ""

                    val consulta = Consulta(id, animal, data, motivo, vet)
                    gerenciador.cadastrarConsulta(consulta)
                    println("Consulta cadastrada com sucesso.")
                }
            }
            "2" -> gerenciador.listarConsultas()
            "3" -> {
                print("ID da consulta: ")
                val idBusca = readLine()?.toIntOrNull()
                if (idBusca == null) {
                    println("ID inválido.")
                } else {
                    val consulta = gerenciador.buscarConsultaPorId(idBusca)
                    if (consulta == null) {
                        println("Consulta não encontrada.")
                    } else {
                        consulta.imprimirResumo()
                    }
                }
            }
            "4" -> {
                print("ID da consulta para marcar como realizada: ")
                val idRealizada = readLine()?.toIntOrNull()
                if (idRealizada == null) {
                    println("ID inválido.")
                } else {
                    val consulta = gerenciador.buscarConsultaPorId(idRealizada)
                    if (consulta == null) {
                        println("Consulta não encontrada.")
                    } else {
                        consulta.setRealizada(true)
                        println("Consulta marcada como realizada.")
                    }
                }
            }
            "0" -> return
            else -> println("Opção inválida.")
        }
    }
}
fun menuClientesGUI(gerenciador: ClienteGerenciador, conexao: Connection) {
    val frame = JFrame("Menu Clientes")
    frame.defaultCloseOperation = JFrame.DISPOSE_ON_CLOSE
    frame.setSize(500, 400)
    frame.layout = BorderLayout()

    val painelBotoes = JPanel(GridLayout(5, 1, 5, 5))
    val painelCentral = JPanel(BorderLayout())
    val areaTexto = JTextArea(10, 40)
    areaTexto.isEditable = false
    val scrollPane = JScrollPane(areaTexto)

    // Botões
    val btnCadastrar = JButton("Cadastrar Cliente")
    val btnListar = JButton("Listar Clientes")
    val btnBuscar = JButton("Buscar Cliente por Nome")
    val btnRemover = JButton("Remover Cliente por Nome e ID")
    val btnFechar = JButton("Fechar")

    // Ações
    btnCadastrar.addActionListener {
        val nome = JOptionPane.showInputDialog(frame, "Nome:")
        val telefone = JOptionPane.showInputDialog(frame, "Telefone:")
        val endereco = JOptionPane.showInputDialog(frame, "Endereço:")
        val cpf = JOptionPane.showInputDialog(frame, "CPF:")

        if (nome != null && telefone != null && endereco != null && cpf != null) {
            val cliente = Cliente(nome, telefone, endereco, cpf)
            gerenciador.adicionarCliente(cliente)
            gerenciador.salvarClienteNoBanco(cliente, conexao)
            areaTexto.text = "Cliente cadastrado com sucesso.\n$nome"
        } else {
            areaTexto.text = "Cadastro cancelado ou incompleto."
        }
    }

    btnListar.addActionListener {
        //val clientesMemoria = gerenciador.listarClientes()
        val clientesBanco = gerenciador.listarClientesBD(conexao)

        val builder = StringBuilder()

        builder.append("\nClientes no Banco:\n")
        for (cliente in clientesBanco) {
            builder.append("- ${cliente.nome}, ${cliente.telefone}, ${cliente.endereco}, ${cliente.getCpf()}\n")
        }

        areaTexto.text = builder.toString()
    }

    btnBuscar.addActionListener {
        val nomeBusca = JOptionPane.showInputDialog(frame, "Nome para buscar:")
        if (nomeBusca != null) {
            val resultados = gerenciador.buscarClientePorNome(nomeBusca)
            val resultadosBD = gerenciador.buscarClientePorNomeBD(conexao, nomeBusca)

            if (resultados.isEmpty()) {
                areaTexto.text = "Nenhum cliente encontrado na memória.\n"
            } else {
                areaTexto.text = "Clientes encontrados na memória:\n"
                resultados.forEach {
                    areaTexto.append("${it.nome} - ${it.telefone} - ${it.endereco}\n")
                }
            }
        }
    }

    btnRemover.addActionListener {
        val nomeRemover = JOptionPane.showInputDialog(frame, "Nome para remover:")
        val idRemoverStr = JOptionPane.showInputDialog(frame, "ID para remover (Banco):")
        val idRemover = idRemoverStr?.toIntOrNull()

        if (idRemover != null) {
            val sucessoBD = gerenciador.removerClientePorIdBD(conexao, idRemover)
            val sucessoMemoria = gerenciador.removerClientePorNome(nomeRemover ?: "")

            val mensagem = StringBuilder()
            if (sucessoBD) mensagem.append("Cliente removido do banco com ID $idRemover.\n")
            if (sucessoMemoria) mensagem.append("Cliente removido da memória com nome $nomeRemover.\n")
            if (!sucessoBD && !sucessoMemoria) mensagem.append("Cliente não encontrado.")

            areaTexto.text = mensagem.toString()
        } else {
            areaTexto.text = "ID inválido."
        }
    }

    btnFechar.addActionListener {
        frame.dispose()
    }

    painelBotoes.add(btnCadastrar)
    painelBotoes.add(btnListar)
    painelBotoes.add(btnBuscar)
    painelBotoes.add(btnRemover)
    painelBotoes.add(btnFechar)

    painelCentral.add(scrollPane, BorderLayout.CENTER)

    frame.add(painelBotoes, BorderLayout.WEST)
    frame.add(painelCentral, BorderLayout.CENTER)

    frame.setLocationRelativeTo(null)
    frame.isVisible = true
}