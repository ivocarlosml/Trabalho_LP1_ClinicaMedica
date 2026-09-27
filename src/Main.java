import java.time.*;

class Main {

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
                    cadastrarPaciente(clinica);
                    voltarMenu();
                    break;
                case 5:
                    cadastrarProfissional(clinica);
                    voltarMenu();
                    break;
                case 6:
                    cadastrarEspecialidade(clinica);
                    voltarMenu();
                    break;
                case 7:
                    consultarConsultasPorPaciente(clinica);
                    voltarMenu();
                    break;
                case 8:
                    consultarHorariosDisponiveis(clinica);
                    voltarMenu();
                    break;
                case 9:
                    clinica.consultarPacientesCadastrados();
                    voltarMenu();
                    break;

                case 10:
                    clinica.consultarProfissionaisCadastrados();
                    voltarMenu();
                    break;

                case 0:
                    IO.println("\n\n OBRIGADO PELO ACESSO!!!\n\n");
                    break;
                default:
                    IO.println("Opção inválida! Tente novamente.");
            }

            IO.println("");

        } while (opcao != 0);
    }

    public void exibirMenu() {
        IO.println(
            "\n========== MENU CLÍNICA MÉDICA ==========\n\n" +
            "1 - AGENDAR CONSULTA\n" +
            "2 - CONCLUIR CONSULTA\n" +
            "3 - CANCELAR CONSULTA\n" +
            "4 - CADASTRAR PACIENTE\n" +
            "5 - CADASTRAR PROFISSIONAL\n" +
            "6 - CADASTRAR ESPECIALIDADE\n" +
            "7 - CONSULTAR CONSULTAS POR PACIENTE\n" +
            "8 - CONSULTAR HORÁRIOS OCUPADOS DO PROFISSIONAL\n" +
            "9 - CONSULTAR PACIENTES CADASTRADOS\n" +
            "10 - CONSULTAR PROFISSIONAIS CADASTRADOS\n" +
            "0 - Sair\n" +
            "Escolha uma opção: "
        );
    }

    public static void cadastrarPaciente(ClinicaMedica clinica) {
        IO.println("\n--- Cadastrar Paciente ---\n");

        IO.print("Nome: ");
        String nome = IO.readln();

        IO.print("CPF: ");
        String cpf = IO.readln();

        Paciente paciente = new Paciente(nome, cpf);
        clinica.cadastrarPaciente(paciente);
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

    public static void cadastrarProfissional(ClinicaMedica clinica) {
        IO.println("--- Cadastrar Profissional ---");

        IO.print("Nome: ");
        String nome = IO.readln();

        IO.print("Registro Profissional(ex: CRM-1234): ");
        String registro = IO.readln();

        IO.print("Nome da especialidade: ");
        String nomeEspecialidade = IO.readln();

        clinica.cadastrarProfissional(nome, registro, nomeEspecialidade);
    }


    public static void agendarConsulta(ClinicaMedica clinica) {
        IO.println("--- Agendar Consulta ---");

        Paciente paciente = clinica.escolherPaciente();

        if (paciente == null) {
            cadastrarPaciente(clinica);
            return;
        }

        Profissional profissional = clinica.escolherProfissional();

        if (profissional == null) {
            return;
        }

        LocalDateTime dataHora = lerDataHora();

        IO.print("Observações: ");
        String observacoes = IO.readln();

        clinica.agendarConsulta(paciente, profissional, dataHora, observacoes);
    }

    public static void cancelarConsulta(ClinicaMedica clinica) {
        IO.println("--- Cancelar Consulta ---");

        IO.print("Registro do profissional (ex: CRM-1234): ");
        String registroProfissional = IO.readln();

        LocalDateTime dataHora = lerDataHora();

        clinica.cancelarConsulta(registroProfissional, dataHora);
    }

    public static void concluirConsulta(ClinicaMedica clinica) {
        IO.println("--- Concluir Consulta ---");

        Profissional profissional = clinica.escolherProfissional();

        if (profissional == null) {
            return;
        }

        clinica.concluirConsulta(profissional);
    }
    public static void consultarHorariosDisponiveis(ClinicaMedica clinica) {
        IO.println("--- Consultar Horários do Profissional ---");
        IO.print("Registro do profissional (ex: CRM-1234): ");
        String registroProfissional = IO.readln();
        clinica.consultarHorariosDisponiveis(registroProfissional);
    }

    public static void consultarConsultasPorPaciente(ClinicaMedica clinica) {
        IO.println("--- Consultar Consultas do Paciente ---");
        IO.print("CPF do paciente: ");
        String cpfPaciente = IO.readln();
        clinica.consultarConsultasPorPaciente(cpfPaciente);
    }
public static LocalDateTime lerDataHora() {

    LocalDate hoje = LocalDate.now();

    while (true) {

        int dia;
        int mes;

        while (true) {
            IO.print("Digite o dia: ");
            String diaStr = IO.readln();

            if (!diaStr.matches("\\d{1,2}")) {
                IO.println("Dia inválido!");
                continue;
            }

            dia = Integer.parseInt(diaStr);

            if (dia < 1 || dia > 31) {
                IO.println("Dia inválido!");
                continue;
            }

            break;
        }

        while (true) {
            IO.print("Digite o mês: ");
            String mesStr = IO.readln();

            if (!mesStr.matches("\\d{1,2}")) {
                IO.println("Mês inválido!");
                continue;
            }

            mes = Integer.parseInt(mesStr);

            if (mes < 1 || mes > 12) {
                IO.println("Mês inválido!");
                continue;
            }

            break;
        }

        int ano = hoje.getYear();

        if (mes < hoje.getMonthValue() ||
            (mes == hoje.getMonthValue() && dia < hoje.getDayOfMonth())) {
            ano++;
        }

        int maxDias = 31;

        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            maxDias = 30;
        } else if (mes == 2) {
            maxDias = 28;

            if (ano % 4 == 0 && (ano % 100 != 0 || ano % 400 == 0)) {
                maxDias = 29;
            }
        }

        if (dia > maxDias) {
            IO.println("Essa data não existe!");
            continue;
        }

        while (true) {

            IO.print("Digite o horário(ex:10:30): ");
            String horaStr = IO.readln();

            if (horaStr.matches("\\d{2}:\\d{2}")) {
                horaStr = horaStr.replace(":", "");
            }

            if (!horaStr.matches("\\d{4}")) {
                IO.println("Horário inválido!");
                continue;
            }

            int hora = Integer.parseInt(horaStr.substring(0, 2));
            int minuto = Integer.parseInt(horaStr.substring(2, 4));

            if (hora > 23 || minuto > 59) {
                IO.println("Horário inválido!");
                continue;
            }

            LocalDateTime dataHora = LocalDateTime.of(
                    ano, mes, dia, hora, minuto
            );

            IO.println("\nData e horário:");
            IO.println(String.format(
                    "%02d/%02d/%04d às %02d:%02d",
                    dia, mes, ano, hora, minuto
            ));

            IO.print("Deseja confirmar? (S/N): ");

            if (IO.readln().equalsIgnoreCase("S")) {
                return dataHora;
            }

            break;
        }
    }
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

        Profissional profissional1 = new Profissional("Dr. Caio Adolfo", "CRM-1111", cardiologia);
        Profissional profissional2 = new Profissional("Dra. Juliana Fonseca", "CRM-2222", pediatria);
        Profissional profissional3 = new Profissional("Dra. Amanda Zckeuwth", "CRM-3333", dermatologia);
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

        clinica.agendarConsulta(paciente1, profissional1, LocalDateTime.of(2026, 9, 25, 9, 0), "Consulta de rotina");
        clinica.agendarConsulta(paciente2, profissional1, LocalDateTime.of(2026, 9, 25, 10, 0), "Consulta de retorno");
        clinica.agendarConsulta(paciente3, profissional2, LocalDateTime.of(2026, 9, 26, 9, 0), "Consulta de rotina");
        clinica.agendarConsulta(paciente4, profissional2, LocalDateTime.of(2026, 9, 26, 10, 0), "Consulta de retorno");
    }
}