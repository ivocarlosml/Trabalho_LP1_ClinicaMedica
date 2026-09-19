class Clinica {
    void main() {
        int opcao = 0;

        // Loop do menu principal
        while (opcao != 9) {
            // Unificado em apenas um IO.println
            IO.println("\n=== SISTEMA DE GESTÃO DA CLÍNICA ===\n" +
                       "1 - Cadastrar Pacientes\n" +
                       "2 - Cadastrar Especialidade\n" +
                       "3 - Cadastrar Profissional\n" +
                       "4 - Agendar Consulta\n" +
                       "5 - Cancelar Consulta\n" +
                       "6 - Concluir Consulta\n" +
                       "7 - Consultar Horários Disponíveis de um Profissional\n" +
                       "8 - Consultar Consultas Agendadas por Paciente\n" +
                       "9 - Sair");
            
            // Lendo a opção do menu no seu formato IO
            opcao = Integer.parseInt(IO.readln("Escolha uma opção: "));

            switch (opcao) {
                case 1:
                    IO.println("\n[Área de Cadastro de Pacientes]");
                    // Código de cadastrar pacientes entrará aqui
                    break;
                case 2:
                    IO.println("\n[Área de Cadastro de Especialidades]");
                    // Código de cadastrar especialidades entrará aqui
                    break;
                case 3:
                    IO.println("\n[Área de Cadastro de Profissionais]");
                    // Código de cadastrar profissionais entrará aqui
                    break;
                case 4:
                    IO.println("\n[Área de Agendamento de Consultas]");
                    // Código de agendar consultas entrará aqui
                    break;
                case 5:
                    IO.println("\n[Área de Cancelamento de Consultas]");
                    // Código de cancelar consultas entrará aqui
                    break;
                case 6:
                    IO.println("\n[Área de Conclusão de Consultas]");
                    // Código de concluir consultas entrará aqui
                    break;
                case 7:
                    IO.println("\n[Área de Consulta de Horários Disponíveis]");
                    // Código de consultar horários entrará aqui
                    break;
                case 8:
                    IO.println("\n[Área de Consultas Agendadas por Paciente]");
                    // Código de listar consultas do paciente entrará aqui
                    break;
                case 9:
                    IO.println("Saindo do sistema... Até logo!");
                    break;
                default:
                    IO.println("Opção inválida! Tente novamente.");
                    break;
            }
            if (opcao != 9) {
                try {
                    Thread.sleep(2000); // 2000 milissegundos = 2 segundos de espera
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        
    }
}
