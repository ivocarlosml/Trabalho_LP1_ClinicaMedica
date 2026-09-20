import java.time.*;

class Main {
    void main() {
        
        ClinicaMedica clinica = new ClinicaMedica("Clínica Saúde Total");

        // Cadastro de apenas 1 de cada, como pedido
        Especialidade especialidade = new Especialidade(1, "Cardiologia", "Cuidados com o coração");
        clinica.cadastrarEspecialidade(especialidade);

        Profissional profissional = new Profissional(1, "Dr. João", "CRM-1234", "77999990000", especialidade);
        clinica.cadastrarProfissional(profissional);

        Paciente paciente = new Paciente(1, "Carlos", "111.111.111-11",LocalDate.of(1995, 5, 20),"77977770000","carlos@email.com","Endereço do Paciente");
        clinica.cadastrarPaciente(paciente);

        IO.println("");

        int opcao;

        do {
            exibirMenu();
            opcao = Integer.parseInt(IO.readln());
            IO.println("");

            switch (opcao) {
                case 1:
                    cadastrarEspecialidade(clinica);
                    break;
                case 2:
                    cadastrarProfissional(clinica);
                    break;
                case 3:
                    clinica.consultarProfissionaisCadastrados();
                    break;
                case 4:
                    agendarConsulta(clinica, paciente, profissional);
                    break;
                case 5:
                    cancelarConsulta(clinica);
                    break;
                case 6:
                    concluirConsulta(clinica);
                    break;
                case 7:
                    clinica.consultarHorariosDisponiveis(profissional);
                    break;
                case 8:
                    clinica.consultarConsultasPorPaciente(paciente.getId());
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

    void exibirMenu() {
        IO.println("========== MENU CLÍNICA MÉDICA ==========");
        IO.println("1 - Cadastrar especialidade");
        IO.println("2 - Cadastrar profissional");
        IO.println("3 - Consultar profissionais cadastrados");
        IO.println("4 - Agendar consulta");
        IO.println("5 - Cancelar consulta");
        IO.println("6 - Concluir consulta");
        IO.println("7 - Consultar horários ocupados do profissional");
        IO.println("8 - Consultar consultas do paciente");
        IO.println("0 - Sair");
        IO.print("Escolha uma opção: ");
    }
    

    static void cadastrarEspecialidade(ClinicaMedica clinica) {
        IO.println("--- Cadastrar Especialidade ---");

        IO.print("Id: ");
        int id = Integer.parseInt(IO.readln());

        IO.print("Nome: ");
        String nome = IO.readln();

        IO.print("Descrição: ");
        String descricao = IO.readln();

        Especialidade especialidade = new Especialidade(id, nome, descricao);
        clinica.cadastrarEspecialidade(especialidade);
    }

    static void cadastrarProfissional(ClinicaMedica clinica) {
        IO.println("--- Cadastrar Profissional ---");

        IO.print("Id: ");
        int id = Integer.parseInt(IO.readln());

        IO.print("Nome: ");
        String nome = IO.readln();

        IO.print("Registro profissional (ex: CRM-1234): ");
        String registro = IO.readln();

        IO.print("Telefone: ");
        String telefone = IO.readln();

        IO.print("Id da especialidade: ");
        int idEspecialidade = Integer.parseInt(IO.readln());

        clinica.cadastrarProfissional(id, nome, registro, telefone, idEspecialidade);
    }

    static void agendarConsulta(ClinicaMedica clinica, Paciente paciente, Profissional profissional) {
        IO.println("--- Agendar Consulta ---");

        IO.print("Ano: ");
        int ano = Integer.parseInt(IO.readln());

        IO.print("Mês: ");
        int mes = Integer.parseInt(IO.readln());

        IO.print("Dia: ");
        int dia = Integer.parseInt(IO.readln());

        IO.print("Hora: ");
        int hora = Integer.parseInt(IO.readln());

        IO.print("Minuto: ");
        int minuto = Integer.parseInt(IO.readln());

        LocalDateTime dataHora = LocalDateTime.of(ano, mes, dia, hora, minuto);

        IO.print("Observações: ");
        String observacoes = IO.readln();

        clinica.agendarConsulta(paciente.getId(), profissional, dataHora, observacoes);
    }

    // Lê o id da consulta e pede para a clínica cancelar
    static void cancelarConsulta(ClinicaMedica clinica) {
        IO.println("--- Cancelar Consulta ---");
        IO.print("Digite o id da consulta: ");
        int id = Integer.parseInt(IO.readln());
        clinica.cancelarConsultaPorId(id);
    }

    // Lê o id da consulta e pede para a clínica concluir
    static void concluirConsulta(ClinicaMedica clinica) {
        IO.println("--- Concluir Consulta ---");
        IO.print("Digite o id da consulta: ");
        int id = Integer.parseInt(IO.readln());
        clinica.concluirConsultaPorId(id);
    }
}