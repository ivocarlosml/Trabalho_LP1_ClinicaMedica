import java.time.*;
class ClinicaMedica {
    private String nome;
    private Paciente[] pacientes = new Paciente[Config.qtdPacientes];
    private int totalPacientes = 0;
    private Profissional[] profissionais = new Profissional[Config.qtdPacientes];
    private int totalProfissionais = 0;
    private Especialidade[] especialidades = new Especialidade[Config.qtdEspecialidades];
    private int totalEspecialidades = 0;
    private Consulta[] consultas = new Consulta[Config.qtdConsultas];
    private int totalConsultas = 0;
    private int proximoIdConsulta = 1;

    ClinicaMedica(String nome) {
        this.nome = nome;
    }

    void cadastrarPaciente(Paciente paciente) {
        if (totalPacientes < pacientes.length) {
            pacientes[totalPacientes] = paciente;
            totalPacientes++;
            IO.println("Paciente " + paciente.getNome() + " cadastrado com sucesso.");
        } else {
            IO.println("Não foi possível cadastrar: limite de pacientes atingido.");
        }
    }

    void cadastrarEspecialidade(Especialidade especialidade) {
        if (totalEspecialidades < especialidades.length) {
            especialidades[totalEspecialidades] = especialidade;
            totalEspecialidades++;
            IO.println("Especialidade " + especialidade.getNome() + " cadastrada com sucesso.");
        } else {
            IO.println("Não foi possível cadastrar: limite de especialidades atingido.");
        }
    }

    // Regra de negócio: profissional deve possuir uma especialidade cadastrada
    void cadastrarProfissional(Profissional profissional) {
        if (profissional.getEspecialidade() == null) {
            IO.println("Não foi possível cadastrar: profissional precisa ter uma especialidade.");
            return;
        }
        if (totalProfissionais < profissionais.length) {
            profissionais[totalProfissionais] = profissional;
            totalProfissionais++;
            IO.println("Profissional " + profissional.getNome() + " cadastrado com sucesso.");
        } else {
            IO.println("Não foi possível cadastrar: limite de profissionais atingido.");
        }
    }

    void cadastrarProfissional(int id, String nome, String registroProfissional, String telefone, int idEspecialidade) {
        Especialidade especialidade = buscarEspecialidadePorId(idEspecialidade);

        if (especialidade == null) {
            IO.println("Não foi possível cadastrar: especialidade não encontrada.");
            return;
        }

        Profissional profissional = new Profissional(id, nome, registroProfissional, telefone, especialidade);
        cadastrarProfissional(profissional);
    }
    // ---------- BUSCAS INTERNAS ----------

    private Paciente buscarPacientePorId(int idPaciente) {
        for (int i = 0; i < totalPacientes; i++) {
            if (pacientes[i].getId() == idPaciente) {
                return pacientes[i];
            }
        }
        return null;
    }

    private Especialidade buscarEspecialidadePorId(int idEspecialidade) {
        for (int i = 0; i < totalEspecialidades; i++) {
            if (especialidades[i].getId() == idEspecialidade) {
                return especialidades[i];
            }
        }
        return null;
    }
    // ---------- AGENDA ----------

    // Regra de negócio: não permitir agendamento para paciente inexistente
    // Regra de negócio: profissional não pode ter duas consultas no mesmo horário
    Consulta agendarConsulta(int idPaciente, Profissional profissional, LocalDateTime dataHora, String observacoes) {
        Paciente paciente = buscarPacientePorId(idPaciente);

        if (paciente == null) {
            IO.println("Não foi possível agendar: paciente não encontrado.");
            return null;
        }

        if (profissional.possuiConsultaNoHorario(dataHora)) {
            IO.println("Não foi possível agendar: profissional já possui consulta nesse horário.");
            return null;
        }

        if (totalConsultas >= consultas.length) {
            IO.println("Não foi possível agendar: limite de consultas da clínica atingido.");
            return null;
        }

        Consulta consulta = new Consulta(proximoIdConsulta, paciente, profissional, dataHora, observacoes);
        proximoIdConsulta++;

        consultas[totalConsultas] = consulta;
        totalConsultas++;

        paciente.adicionarConsulta(consulta);
        profissional.adicionarConsulta(consulta);

        IO.println("Consulta agendada com sucesso para " + paciente.getNome() + " em " + dataHora);
        return consulta;
    }

    void cancelarConsulta(Consulta consulta) {
        consulta.cancelar();
    }

    void concluirConsulta(Consulta consulta) {
        consulta.concluir();
    }

    // ---------- CONSULTAS DE INFORMAÇÃO ----------

    void consultarHorariosDisponiveis(Profissional profissional) {
        profissional.listarHorariosOcupados();
    }

    void consultarConsultasPorPaciente(int idPaciente) {
        Paciente paciente = buscarPacientePorId(idPaciente);
        if (paciente == null) {
            IO.println("Paciente não encontrado.");
            return;
        }
        paciente.listarConsultas();
    }
    // Lista todos os profissionais cadastrados na clínica
    void consultarProfissionaisCadastrados() {
        IO.println("Profissionais cadastrados:");
        if (totalProfissionais == 0) {
            IO.println("Nenhum profissional cadastrado.");
            return;
        }
        for (int i = 0; i < totalProfissionais; i++) {
            profissionais[i].exibir();
        }
    }
        // Busca uma consulta pelo id (usado pelo menu, que só sabe o id digitado pelo usuário)
    private Consulta buscarConsultaPorId(int idConsulta) {
        for (int i = 0; i < totalConsultas; i++) {
            if (consultas[i].getId() == idConsulta) {
                return consultas[i];
            }
        }
        return null;
    }
    void cancelarConsultaPorId(int idConsulta) {
        Consulta consulta = buscarConsultaPorId(idConsulta);
        if (consulta == null) {
            IO.println("Consulta não encontrada.");
            return;
        }
        consulta.cancelar();
    }
 
    void concluirConsultaPorId(int idConsulta) {
        Consulta consulta = buscarConsultaPorId(idConsulta);
        if (consulta == null) {
            IO.println("Consulta não encontrada.");
            return;
        }
        consulta.concluir();
    }

    // Gets
    String getNome() {
        return nome;
    }

    Paciente[] getPacientes() {
        return pacientes;
    }

    Profissional[] getProfissionais() {
        return profissionais;
    }

    Especialidade[] getEspecialidades() {
        return especialidades;
    }

    Consulta[] getConsultas() {
        return consultas;
    }
}
  
