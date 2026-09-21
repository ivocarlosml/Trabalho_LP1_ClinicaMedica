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
                    break;
                case 2:
                    concluirConsulta(clinica);
                    break;
                case 3:
                    cancelarConsulta(clinica);
                    break;
                case 4:
                    cadastrarPaciente(clinica);
                    break;
                case 5:
                    cadastrarProfissional(clinica);
                    break;
                case 6:
                    cadastrarEspecialidade(clinica);
                    break;
                case 7:
                    consultarConsultasPorPaciente(clinica);
                    break;
                case 8:
                    consultarHorariosDisponiveis(clinica);
                    break;
                case 9:
                    clinica.consultarPacientesCadastrados();
                    break;

                case 10:
                    clinica.consultarProfissionaisCadastrados();
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
            "========== MENU CLÍNICA MÉDICA ==========\n" +
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
        IO.println("--- Cadastrar Paciente ---");

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

        IO.print("Registro profissional (ex: CRM-1234): ");
        String registro = IO.readln();

        IO.print("Nome da especialidade: ");
        String nomeEspecialidade = IO.readln();

        clinica.cadastrarProfissional(nome, registro, nomeEspecialidade);
    }

    public static void agendarConsulta(ClinicaMedica clinica) {
        IO.println("--- Agendar Consulta ---");

        IO.print("CPF do paciente: ");
        String cpfPaciente = IO.readln();

        IO.print("Registro do profissional (ex: CRM-1234): ");
        String registroProfissional = IO.readln();

        LocalDateTime dataHora = lerDataHora();

        IO.print("Observações: ");
        String observacoes = IO.readln();

        clinica.agendarConsulta(cpfPaciente, registroProfissional, dataHora, observacoes);
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

        IO.print("Registro do profissional (ex: CRM-1234): ");
        String registroProfissional = IO.readln();

        LocalDateTime dataHora = lerDataHora();

        clinica.concluirConsulta(registroProfissional, dataHora);
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

    public static LocalDate lerData() {
        IO.print("Ano de nascimento: ");
        int ano = Integer.parseInt(IO.readln());

        IO.print("Mês de nascimento: ");
        int mes = Integer.parseInt(IO.readln());

        IO.print("Dia de nascimento: ");
        int dia = Integer.parseInt(IO.readln());

        return LocalDate.of(ano, mes, dia);
    }

    public static LocalDateTime lerDataHora() {
        int dia = 0, mes = 0, ano = 0, hora = 0, minuto = 0;

        while (true) {
            IO.print("Digite a data (ex:12/12/2026): ");
            String dataStr = IO.readln(); // Lê a digitação do usuário

            dataStr = dataStr.replace("/", ""); 

            if (dataStr.length() == 8) {
                dia = Integer.parseInt(dataStr.substring(0, 2));
                mes = Integer.parseInt(dataStr.substring(2, 4));
                ano = Integer.parseInt(dataStr.substring(4, 8));


                if (ano >= 2026 && ano <= 2100) {
                    
                    if (mes >= 1 && mes <= 12) {
                        
                        int maxDias = 31;
                        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                            maxDias = 30;
                        } else if (mes == 2) {
                            if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
                                maxDias = 29;
                            } else {
                                maxDias = 28;
                            }
                        }
                        if (dia >= 1 && dia <= maxDias) {
                            break;
                        }
                    }
                }
            }
            IO.println("Data inválida! Verifique o dia, mês ou ano (Mínimo ano 2026).");
        }

        while (true) {
            IO.print("Digite o horário (ex:12:20): ");
            String horaStr = IO.readln();

            horaStr = horaStr.replace(":", "");

            if (horaStr.length() == 4) {
                hora = Integer.parseInt(horaStr.substring(0, 2));
                minuto = Integer.parseInt(horaStr.substring(2, 4));

                if (hora >= 0 && hora <= 23 && minuto >= 0 && minuto <= 59) {
                    break;
                }
            }
            IO.println("Horário inválido! Digite valores reais entre 00:00 e 23:59.");
        }

        return LocalDateTime.of(ano, mes, dia, hora, minuto);
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

        clinica.agendarConsulta(paciente1.getCpf(), profissional1, LocalDateTime.of(2026, 9, 25, 9, 0), "Consulta de rotina");
        clinica.agendarConsulta(paciente2.getCpf(), profissional1, LocalDateTime.of(2026, 9, 25, 10, 0), "Consulta de retorno");
        clinica.agendarConsulta(paciente3.getCpf(), profissional2, LocalDateTime.of(2026, 9, 26, 9, 0), "Consulta de rotina");
        clinica.agendarConsulta(paciente4.getCpf(), profissional2, LocalDateTime.of(2026, 9, 26, 10, 0), "Consulta de retorno");
    }
}