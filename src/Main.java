import java.time.*;
    ClinicaMedica clinica = new ClinicaMedica("Clínica Bom Cuidado");

    public void main() {

        dadosDeTeste();

        int opcao;

        do {
            exibirMenu();
            opcao = Integer.parseInt(IO.readln());
            IO.println("");

            switch (opcao) {
                case 1:
                    agendarConsulta(clinica);
                    voltarMenu();
                    break;
                case 2:
                    concluirConsulta(clinica);
                    voltarMenu();
                    break;
                case 3:
                    cancelarConsulta(clinica);
                    voltarMenu();
                    break;
                case 4:
                    cadastrarProfissional(clinica);
                    voltarMenu();
                    break;
                case 5:
                    consultarConsultasPorPaciente(clinica);
                    voltarMenu();
                    break;
                case 6:
                    consultarHorariosOcupados(clinica);
                    voltarMenu();
                    break;
                case 7:
                    clinica.consultarPacientesCadastrados();
                    voltarMenu();
                    break;

                case 8:
                    clinica.consultarProfissionaisCadastrados();
                    voltarMenu();
                    break;
                
                case 9:
                    clinica.consultarEspecialidadesCadastradas();
                    voltarMenu();
                    break;

                case 0:
                    IO.println("\n\n OBRIGADO PELO ACESSO!!!\n\n");
                    break;
                default:
                    IO.println("Opção inválida! Tente novamente.");
                    voltarMenu();
            }

            IO.println("");

        } while (opcao != 0);
    }

    public void exibirMenu() {
        IO.print(
            "\n========== CLÍNICA MÉDICA ==========\n\n" +
            "1 - AGENDAR CONSULTA\n" +
            "2 - CONCLUIR CONSULTA\n" +
            "3 - CANCELAR CONSULTA\n" +
            "4 - CADASTRAR PROFISSIONAL\n" +
            "5 - CONSULTAR CONSULTAS POR PACIENTE\n" +
            "6 - CONSULTAR HORÁRIOS OCUPADOS\n" +
            "7 - CONSULTAR PACIENTES\n" +
            "8 - CONSULTAR PROFISSIONAIS\n" +
            "9 - CONSULTAR ESPECIALIDADES\n" +
            "0 - Sair\n" +
            "Escolha uma opção: "
        );
    }

    public static void agendarConsulta(ClinicaMedica clinica) {
        IO.println("\n--- Agendar Consulta ---\n");

        Paciente paciente = clinica.escolherPaciente();

        if (paciente == null) {
            cadastrarPaciente(clinica);
            return;
        }

        Especialidade especialidade = clinica.escolherEspecialidade(false);

        if (especialidade == null) {
            return;
        }

        Profissional profissional = clinica.escolherProfissional(especialidade);

        if (profissional == null) {
            return;
        }

        LocalDateTime dataHora = Config.lerDataHora();

        IO.print("Observações: ");
        String observacoes = IO.readln();

        clinica.agendarConsulta(paciente, profissional, dataHora, observacoes);
    }

    public static void concluirConsulta(ClinicaMedica clinica) {
        IO.println("--- Concluir Consulta ---");

        IO.print(
            "\n1 - Escolher por paciente\n" +
            "2 - Escolher por profissional\n" +
            "0 - Voltar\n" +
            "Escolha uma opção: "
        );

        String opcao = IO.readln();

        if (!opcao.matches("\\d+")) {
            IO.println("Opção inválida!");
            return;
        }

        int escolha = Integer.parseInt(opcao);

        switch (escolha) {
            case 1:
                clinica.concluirConsultaPorPaciente();
                break;

            case 2:
                clinica.concluirConsultaPorProfissional();
                break;

            case 0:
                return;

            default:
                IO.println("Opção inválida!");
        }
    }

    public static void cancelarConsulta(ClinicaMedica clinica) {
        IO.println("--- Cancelar Consulta ---");

        IO.print(
            "\n1 - Escolher por paciente\n" +
            "2 - Escolher por profissional\n" +
            "0 - Voltar\n" +
            "Escolha uma opção: "
        );

        String opcao = IO.readln();

        if (!opcao.matches("\\d+")) {
            IO.println("Opção inválida!");
            return;
        }

        int escolha = Integer.parseInt(opcao);

        switch (escolha) {
            case 1:
                clinica.cancelarConsultaPorPaciente();
                break;

            case 2:
                clinica.cancelarConsultaPorProfissional();
                break;

            case 0:
                return;

            default:
                IO.println("Opção inválida!");
        }
    }

    public static void cadastrarProfissional(ClinicaMedica clinica) {
        IO.println("--- Cadastrar Profissional ---");

        IO.print("Nome: ");
        String nome = IO.readln();

        IO.print("Registro Profissional(ex: CRM-1234): ");
        String registro = IO.readln();

        Especialidade especialidade;

        while (true) {
            especialidade = clinica.escolherEspecialidade(true);

            if (especialidade != null) {
                break;
            }

            cadastrarEspecialidade(clinica);
        }

        Profissional profissional = new Profissional(nome, registro, especialidade);

        clinica.cadastrarProfissional(profissional);
    }

    public static void consultarConsultasPorPaciente(ClinicaMedica clinica) {
        IO.println("--- Consultar Consultas do Paciente ---");
        IO.print("Nome do Paciente: ");
        String nomePaciente = IO.readln();

        clinica.consultarConsultasPorPaciente(nomePaciente);
    }

    public static void consultarHorariosOcupados(ClinicaMedica clinica) {
        IO.println("--- Horários Ocupados: ---");
        clinica.consultarHorariosOcupados();
    }
    public static void cadastrarEspecialidade(ClinicaMedica clinica) {
        IO.println("--- Cadastrar Especialidade ---");

        IO.print("Nome: ");
        String nome = IO.readln();

        IO.print("Descrição: ");
        String descricao = IO.readln();

        Especialidade especialidade = new Especialidade(nome, descricao);
        clinica.cadastrarEspecialidade(especialidade);
    }
    
    public static void cadastrarPaciente(ClinicaMedica clinica) {
        IO.println("\n--- Cadastrar Paciente ---\n");

        IO.print("Nome: ");
        String nome = IO.readln();

        IO.print("CPF: ");
        String cpf = IO.readln();

        Paciente paciente = new Paciente(nome, cpf);
        clinica.cadastrarPaciente(paciente);
        agendarConsulta(clinica);
    }

    public static void voltarMenu() {
        IO.println("\nPressione ENTER para voltar ao menu.");
        IO.readln();
    }


    public void dadosDeTeste() {
        Especialidade cardiologia = new Especialidade("Cardiologia", "Cuidados com o coração e o sistema circulatório.");
        Especialidade pediatria = new Especialidade("Pediatria", "Cuidados com crianças e adolescentes.");
        Especialidade dermatologia = new Especialidade("Dermatologia", "Cuidados com a pele, unhas e cabelos.");
        clinica.cadastrarEspecialidade(cardiologia);
        clinica.cadastrarEspecialidade(pediatria);
        clinica.cadastrarEspecialidade(dermatologia);

        Profissional profissional1 = new Profissional("Dr. Caio Adolfo", "111111", cardiologia);
        Profissional profissional2 = new Profissional("Dra. Juliana Fonseca", "222211", pediatria);
        Profissional profissional3 = new Profissional("Dra. Amanda Zckeuwth", "223333", dermatologia);
        clinica.cadastrarProfissional(profissional1);
        clinica.cadastrarProfissional(profissional2);
        clinica.cadastrarProfissional(profissional3);

        Paciente paciente1 = new Paciente("Jorge Mateus Bénicio Silva", "111.333.333-33");
        Paciente paciente2 = new Paciente("Maria Luiza Santana", "111.444.444-44");
        Paciente paciente3 = new Paciente("José Bandeira Cambuí", "111.555.555-55");
        Paciente paciente4 = new Paciente("Amelie Soares Bittencourt", "666.666.666-66");

        clinica.cadastrarPaciente(paciente1);
        clinica.cadastrarPaciente(paciente2);
        clinica.cadastrarPaciente(paciente3);
        clinica.cadastrarPaciente(paciente4);

        clinica.agendarConsulta(paciente1, profissional1, LocalDateTime.of(2026, 12, 21, 9, 0), "Consulta de rotina");
        clinica.agendarConsulta(paciente2, profissional1, LocalDateTime.of(2026, 12, 14, 10, 0), "Consulta de retorno");
        clinica.agendarConsulta(paciente3, profissional2, LocalDateTime.of(2026, 11, 9, 9, 0), "Consulta de rotina");
        clinica.agendarConsulta(paciente4, profissional2, LocalDateTime.of(2027, 1, 7, 10, 0), "Consulta de retorno");
}