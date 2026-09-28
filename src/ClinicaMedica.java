import java.time.*;

public class ClinicaMedica {

    private String nome;
    private Paciente[] pacientes = new Paciente[Config.qtdPacientes];
    private int totalPacientes = 0;
    private Profissional[] profissionais = new Profissional[Config.qtdProfissionais];
    private int totalProfissionais = 0;
    private Especialidade[] especialidades = new Especialidade[Config.qtdEspecialidades];
    private int totalEspecialidades = 0;
    private Consulta[] consultas = new Consulta[Config.qtdConsultas];
    private int totalConsultas = 0;

    public ClinicaMedica(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }

    public Paciente[] getPacientes() {
        return pacientes;
    }

    public Profissional[] getProfissionais() {
        return profissionais;
    }

    public Especialidade[] getEspecialidades() {
        return especialidades;
    }

    public Consulta[] getConsultas() {
        return consultas;
    }

    private void adicionarPaciente(Paciente paciente) {
        pacientes[totalPacientes] = paciente;
        totalPacientes++;
        IO.println("\nPaciente " + paciente.getNome() + " cadastrado com sucesso.");
    }

    private void adicionarProfissional(Profissional profissional) {
        profissionais[totalProfissionais] = profissional;
        totalProfissionais++;
        IO.println("Profissional " + profissional.getNome() + " cadastrado com sucesso.");
    }

    private void adicionarEspecialidade(Especialidade especialidade) {
        especialidades[totalEspecialidades] = especialidade;
        totalEspecialidades++;
        IO.println("Especialidade " + especialidade.getNome() + " cadastrada com sucesso.");
    }

    private void adicionarConsulta(Consulta consulta) {
        consultas[totalConsultas] = consulta;
        totalConsultas++;
    }

    public void cadastrarPaciente(Paciente paciente) {
        if (totalPacientes < pacientes.length) {
            adicionarPaciente(paciente);
        } else {
            IO.println("Não foi possível cadastrar: limite de pacientes atingido.");
        }
    }

    public void cadastrarEspecialidade(Especialidade especialidade) {
        if (totalEspecialidades < especialidades.length) {
            adicionarEspecialidade(especialidade);
        } else {
            IO.println("Não foi possível cadastrar: limite de especialidades atingido.");
        }
    }

    public void cadastrarProfissional(Profissional profissional) {
        if (profissional.getEspecialidade() == null) {
            IO.println("Não foi possível cadastrar: profissional precisa ter uma especialidade.");
            return;
        }

        if (totalProfissionais < profissionais.length) {
            adicionarProfissional(profissional);
        } else {
            IO.println("Não foi possível cadastrar: limite de profissionais atingido.");
        }
    }

    public Paciente buscarPacientePorNome(String nome) {
        for (int i = 0; i < totalPacientes; i++) {
            if (pacientes[i].getNome().equalsIgnoreCase(nome)) {
                return pacientes[i];
            }
        }

        return null;
    }

    public Consulta agendarConsulta(Paciente paciente, Profissional profissional, LocalDateTime dataHora, String observacoes) {

        if (profissional.possuiConsultaNoHorario(dataHora)) {
            IO.println("Não foi possível agendar: profissional já possui consulta nesse horário.");
            return null;
        }

        if (totalConsultas >= consultas.length) {
            IO.println("Não foi possível agendar: limite de consultas da clínica atingido.");
            return null;
        }

        Consulta consulta = new Consulta(paciente, profissional, dataHora, observacoes);

        adicionarConsulta(consulta);

        paciente.adicionarConsulta(consulta);
        profissional.adicionarConsulta(consulta);

        consulta.exibir();

        return consulta;
    }

    public Paciente escolherPaciente() {

        listarPacientes();

        IO.println("[0] Cadastrar um novo paciente\n");

        while (true) {
            IO.print("Escolha o paciente: ");
            String opcao = IO.readln();

            if (!opcao.matches("\\d+")) {
                IO.println("Opção inválida!");
                continue;
            }

            int numero = Integer.parseInt(opcao);

            if (numero == 0) {
                return null;
            }

            if (numero < 1 || numero > totalPacientes) {
                IO.println("Opção inválida!");
                continue;
            }

            return pacientes[numero - 1];
        }
    }

    public Especialidade escolherEspecialidade(boolean permitirCadastro) {
        IO.println("\n---Especialidades---\n");

        if (totalEspecialidades == 0) {
            IO.println("Nenhuma especialidade cadastrada.");
            return null;
        }

        for (int i = 0; i < totalEspecialidades; i++) {
            IO.println("[" + (i + 1) + "] "
                    + especialidades[i].getNome()
                    + " - "
                    + especialidades[i].getDescricao());
        }

        if(permitirCadastro){
            IO.println("[0] Cadastrar Especialidade\n");
        }else{
            IO.println("[0] Voltar\n");
        }

        while (true) {
            IO.print("Escolha a especialidade: ");
            String opcao = IO.readln();

            if (!opcao.matches("\\d+")) {
                IO.println("Opção inválida!");
                continue;
            }

            int numero = Integer.parseInt(opcao);

            if (numero == 0) {
                return null;
            }

            if (numero < 1 || numero > totalEspecialidades) {
                IO.println("Opção inválida!");
                continue;
            }

            return especialidades[numero - 1];
        }
    }

    public Profissional escolherProfissional(Especialidade especialidade) {
        IO.println("\nProfissionais de " + especialidade.getNome() + ":");

        int quantidade = 0;

        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].getEspecialidade() == especialidade) {
                quantidade++;
                IO.println("[" + quantidade + "] "
                        + profissionais[i].getNome()
                        + " | "
                        + profissionais[i].getRegistroProfissional());
            }
        }

        if (quantidade == 0) {
            IO.println("Nenhum profissional cadastrado para esta especialidade.");
            return null;
        }

        IO.println("[0] Voltar\n");

        while (true) {
            IO.print("Escolha o profissional: ");
            String opcao = IO.readln();

            if (!opcao.matches("\\d+")) {
                IO.println("Opção inválida!");
                continue;
            }

            int numero = Integer.parseInt(opcao);

            if (numero == 0) {
                return null;
            }

            if (numero < 1 || numero > quantidade) {
                IO.println("Opção inválida!");
                continue;
            }

            int contador = 0;

            for (int i = 0; i < totalProfissionais; i++) {
                if (profissionais[i].getEspecialidade() == especialidade) {
                    contador++;

                    if (contador == numero) {
                        return profissionais[i];
                    }
                }
            }
        }
    }

    public void cancelarConsulta(Consulta consulta) {
        consulta.cancelar();
    }

    public void concluirConsulta(Consulta consulta) {
        consulta.concluir();
    }

    public void concluirConsultaPorPaciente() {
        Paciente paciente = escolherPaciente();

        if (paciente == null) {
            return;
        }

        int quantidade = 0;

        IO.println("\nConsultas de " + paciente.getNome() + ":" );

        for (int i = 0; i < totalConsultas; i++) {
            Consulta consulta = consultas[i];

            if (consulta.getPaciente() == paciente) {
                quantidade++;
                IO.println("[" + quantidade + "] " + consulta.getPaciente().getNome() + " | " + consulta.getDataHora().format(Config.formatoDataHora)+ " |" + consulta.getStatus());
        
            }
        }

        if (quantidade == 0) {
            IO.println("Nenhuma consulta encontrada.");
            return;
        }

        IO.println("[0] Voltar");

        while (true) {
            IO.print("Escolha a consulta: ");
            String opcao = IO.readln();

            if (!opcao.matches("\\d+")) {
                IO.println("Opção inválida!");
                continue;
            }

            int escolha = Integer.parseInt(opcao);

            if (escolha == 0) {
                return;
            }

            if (escolha < 1 || escolha > quantidade) {
                IO.println("Opção inválida!");
                continue;
            }

            int numero = 0;

            for (int i = 0; i < totalConsultas; i++) {
                Consulta consulta = consultas[i];

                if (consulta.getPaciente() == paciente) {
                    numero++;

                    if (numero == escolha) {
                        concluirConsulta(consulta);
                        return;
                    }
                }
            }
        }
    }
 
    public void concluirConsultaPorProfissional() {
        Profissional profissional;

        while (true) {
            listarProfissionais();

            if (totalProfissionais == 0) {
                return;
            }

            IO.println("\n[0] Voltar");
            IO.print("Escolha o profissional: ");

            String opcao = IO.readln();

            if (!opcao.matches("\\d+")) {
                IO.println("Opção inválida!");
                continue;
            }

            int numero = Integer.parseInt(opcao);

            if (numero == 0) {
                return;
            }

            if (numero < 1 || numero > totalProfissionais) {
                IO.println("Opção inválida!");
                continue;
            }

            profissional = profissionais[numero - 1];
            break;
        }

        int quantidade = 0;


        IO.println("\nConsultas de "+ profissional.getNome() + ":");

        for (int i = 0; i < totalConsultas; i++) {
            Consulta consulta = consultas[i];

            if (consulta.getProfissional() == profissional) {
                quantidade++;
                IO.println("[" + quantidade + "] " + consulta.getPaciente().getNome() + " | " + consulta.getDataHora().format(Config.formatoDataHora)+ " |" + consulta.getStatus());
            }
        }

        if (quantidade == 0) {
            IO.println("Nenhuma consulta encontrada.");
            return;
        }

        IO.println("[0] Voltar");

        while (true) {
            IO.print("Escolha a consulta: ");
            String opcao = IO.readln();

            if (!opcao.matches("\\d+")) {
                IO.println("Opção inválida!");
                continue;
            }

            int escolha = Integer.parseInt(opcao);

            if (escolha == 0) {
                return;
            }

            if (escolha < 1 || escolha > quantidade) {
                IO.println("Opção inválida!");
                continue;
            }

            int numero = 0;

            for (int i = 0; i < totalConsultas; i++) {
                Consulta consulta = consultas[i];

                if (consulta.getProfissional() == profissional) {
                    numero++;

                    if (numero == escolha) {
                        concluirConsulta(consulta);
                        return;
                    }
                }
            }
        }
    }

    public void cancelarConsultaPorPaciente() {
    Paciente paciente = escolherPaciente();

    if (paciente == null) {
        return;
    }

    int quantidade = 0;

    IO.println("\nConsultas de " + paciente.getNome() + ":");

    for (int i = 0; i < totalConsultas; i++) {
        Consulta consulta = consultas[i];

        if (consulta.getPaciente() == paciente) {
            quantidade++;
            IO.println("[" + quantidade + "] " + consulta.getProfissional().getNome() + " | " + consulta.getDataHora().format(Config.formatoDataHora)+ " |" + consulta.getStatus());
        }
    }

    if (quantidade == 0) {
        IO.println("Nenhuma consulta encontrada.");
        return;
    }

    IO.println("[0] Voltar");

    while (true) {
        IO.print("Escolha a consulta: ");
        String opcao = IO.readln();

        if (!opcao.matches("\\d+")) {
            IO.println("Opção inválida!");
            continue;
        }

        int escolha = Integer.parseInt(opcao);

        if (escolha == 0) {
            return;
        }

        if (escolha < 1 || escolha > quantidade) {
            IO.println("Opção inválida!");
            continue;
        }

        int numero = 0;

        for (int i = 0; i < totalConsultas; i++) {
            Consulta consulta = consultas[i];

            if (consulta.getPaciente() == paciente) {
                numero++;

                if (numero == escolha) {
                    cancelarConsulta(consulta);
                    return;
                }
            }
        }
    }
}
 
    public void cancelarConsultaPorProfissional() {
    Profissional profissional;

    while (true) {
        listarProfissionais();

        if (totalProfissionais == 0) {
            return;
        }

        IO.println("\n[0] Voltar");

        IO.print("Escolha o profissional: ");
        String opcao = IO.readln();

        if (!opcao.matches("\\d+")) {
            IO.println("Opção inválida!");
            continue;
        }

        int numero = Integer.parseInt(opcao);

        if (numero == 0) {
            return;
        }

        if (numero < 1 || numero > totalProfissionais) {
            IO.println("Opção inválida!");
            continue;
        }

        profissional = profissionais[numero - 1];
        break;
    }

    int quantidade = 0;

    IO.println("\nConsultas de " + profissional.getNome()+":");

    for (int i = 0; i < totalConsultas; i++) {
        Consulta consulta = consultas[i];

        if (consulta.getProfissional() == profissional) {
            quantidade++;
            IO.println("[" + quantidade + "] " + consulta.getPaciente().getNome() + " | " + consulta.getDataHora().format(Config.formatoDataHora)+ " |" + consulta.getStatus());
        }
    }

    if (quantidade == 0) {
        IO.println("Nenhuma consulta encontrada.");
        return;
    }

    IO.println("[0] Voltar");

    while (true) {
        IO.print("Escolha a consulta: ");
        String opcao = IO.readln();

        if (!opcao.matches("\\d+")) {
            IO.println("Opção inválida!");
            continue;
        }

        int escolha = Integer.parseInt(opcao);

        if (escolha == 0) {
            return;
        }

        if (escolha < 1 || escolha > quantidade) {
            IO.println("Opção inválida!");
            continue;
        }

        int numero = 0;

        for (int i = 0; i < totalConsultas; i++) {
            Consulta consulta = consultas[i];

            if (consulta.getProfissional() == profissional) {
                numero++;

                if (numero == escolha) {
                    cancelarConsulta(consulta);
                    return;
                }
            }
        }
    }
}

    public void consultarHorariosOcupados() {
        listarProfissionais();

        if (totalProfissionais == 0) {
            return;
        }

        IO.println("[0] Voltar\n");

        while (true) {
            IO.print("Escolha o profissional: ");
            String opcao = IO.readln();

            if (!opcao.matches("\\d+")) {
                IO.println("Opção inválida!");
                continue;
            }

            int numero = Integer.parseInt(opcao);

            if (numero == 0) {
                return;
            }

            if (numero < 1 || numero > totalProfissionais) {
                IO.println("Opção inválida!");
                continue;
            }

            Profissional profissional = profissionais[numero - 1];

            IO.println("\nHorários ocupados de " + profissional.getNome() + ":");
            profissional.listarHorariosOcupados();

            return;
        }
    }

    public void consultarConsultasPorPaciente(String nomePaciente) {
        Paciente paciente = buscarPacientePorNome(nomePaciente);

        if (paciente == null) {
            IO.println("Paciente não encontrado.");
            return;
        }

        paciente.listarConsultas();
    }

    private void listarPacientes() {
        IO.println("Pacientes cadastrados:\n");

        if (totalPacientes == 0) {
            IO.println("Nenhum paciente cadastrado.");
            return;
        }

        for (int i = 0; i < totalPacientes; i++) {
            IO.println("[" + (i + 1) + "] "
                    + pacientes[i].getNome());
        }
    }

    private void listarProfissionais() {
        IO.println("\nProfissionais cadastrados:");

        if (totalProfissionais == 0) {
            IO.println("Nenhum profissional cadastrado.");
            return;
        }

        for (int i = 0; i < totalProfissionais; i++) {
            IO.println("[" + (i + 1) + "] "
                    + profissionais[i].getNome()
                    + " | "
                    + profissionais[i].getEspecialidade().getNome());
        }
    }

    public void consultarPacientesCadastrados() {
        IO.println("Pacientes cadastrados:\n");

        if (totalPacientes == 0) {
            IO.println("Nenhum paciente cadastrado.");
            return;
        }

        for (int i = 0; i < totalPacientes; i++) {
            pacientes[i].exibir();
        }
    }

    public void consultarProfissionaisCadastrados() {
        IO.println("=== Profissionais cadastrados ===\n");

        if (totalProfissionais == 0) {
            IO.println("Nenhum profissional cadastrado.");
            return;
        }

        for (int i = 0; i < totalProfissionais; i++) {
            profissionais[i].exibir();
        }
    }

    public void consultarEspecialidadesCadastradas() {
        IO.println("\n----Especialidades cadastradas----\n");

        if (totalEspecialidades == 0) {
            IO.println("Nenhuma especialidade cadastrada.");
            return;
        }

        for (int i = 0; i < totalEspecialidades; i++) {
            especialidades[i].exibir();
        }
    }
}