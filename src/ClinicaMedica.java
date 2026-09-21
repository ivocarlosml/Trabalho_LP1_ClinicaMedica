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
        IO.println("Paciente " + paciente.getNome() + " cadastrado com sucesso.");
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

    public void cadastrarProfissional(String nome, String registroProfissional, String nomeEspecialidade) {
        Especialidade especialidade = buscarEspecialidadePorNome(nomeEspecialidade);

        if (especialidade == null) {
            IO.println("Não foi possível cadastrar: especialidade não encontrada.");
            return;
        }

        Profissional profissional = new Profissional(nome, registroProfissional, especialidade);
        cadastrarProfissional(profissional);
    }

    private Paciente buscarPacientePorCpf(String cpf) {
        for (int i = 0; i < totalPacientes; i++) {
            if (pacientes[i].getCpf().equals(cpf)) {
                return pacientes[i];
            }
        }
        return null;
    }

    private Especialidade buscarEspecialidadePorNome(String nome) {
        for (int i = 0; i < totalEspecialidades; i++) {
            if (especialidades[i].getNome().equalsIgnoreCase(nome)) {
                return especialidades[i];
            }
        }
        return null;
    }

    private Profissional buscarProfissionalPorRegistro(String registroProfissional) {
        for (int i = 0; i < totalProfissionais; i++) {
            if (profissionais[i].getRegistroProfissional().equals(registroProfissional)) {
                return profissionais[i];
            }
        }
        return null;
    }

    private Consulta buscarConsulta(String registroProfissional, LocalDateTime dataHora) {
        for (int i = 0; i < totalConsultas; i++) {
            Consulta c = consultas[i];
            if (c.getProfissional().getRegistroProfissional().equals(registroProfissional)
                    && c.getDataHora().equals(dataHora)) {
                return c;
            }
        }
        return null;
    }

    public Consulta agendarConsulta(String cpfPaciente, Profissional profissional, LocalDateTime dataHora, String observacoes) {
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);

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

        Consulta consulta = new Consulta(paciente, profissional, dataHora);
        adicionarConsulta(consulta);

        paciente.adicionarConsulta(consulta);
        profissional.adicionarConsulta(consulta);

        IO.println("Consulta agendada com sucesso para " + paciente.getNome() + " em " + dataHora);
        return consulta;
    }

    //Aplicação de recursividade

    public Consulta agendarConsulta(String cpfPaciente, String registroProfissional, LocalDateTime dataHora, String observacoes) {
        Profissional profissional = buscarProfissionalPorRegistro(registroProfissional);

        if (profissional == null) {
            IO.println("Não foi possível agendar: profissional não encontrado.");
            return null;
        }

        return agendarConsulta(cpfPaciente, profissional, dataHora, observacoes);
    }

    public void cancelarConsulta(Consulta consulta) {
        consulta.cancelar();
    }

    public void concluirConsulta(Consulta consulta) {
        consulta.concluir();
    }

    public void cancelarConsulta(String registroProfissional, LocalDateTime dataHora) {
        Consulta consulta = buscarConsulta(registroProfissional, dataHora);
        if (consulta == null) {
            IO.println("Consulta não encontrada.");
            return;
        }
        cancelarConsulta(consulta);
    }

    public void concluirConsulta(String registroProfissional, LocalDateTime dataHora) {
        Consulta consulta = buscarConsulta(registroProfissional, dataHora);
        if (consulta == null) {
            IO.println("Consulta não encontrada.");
            return;
        }
        concluirConsulta(consulta);
    }

    public void consultarHorariosDisponiveis(Profissional profissional) {
        profissional.listarHorariosOcupados();
    }

    public void consultarHorariosDisponiveis(String registroProfissional) {
        Profissional profissional = buscarProfissionalPorRegistro(registroProfissional);
        if (profissional == null) {
            IO.println("Profissional não encontrado.");
            return;
        }
        consultarHorariosDisponiveis(profissional);
    }

    public void consultarConsultasPorPaciente(String cpfPaciente) {
        Paciente paciente = buscarPacientePorCpf(cpfPaciente);
        if (paciente == null) {
            IO.println("Paciente não encontrado.");
            return;
        }
        paciente.listarConsultas();
    }

    public void consultarPacientesCadastrados() {
        IO.println("Pacientes cadastrados:");

        if (totalPacientes == 0) {
            IO.println("Nenhum paciente cadastrado.");
            return;
        }

        for (int i = 0; i < totalPacientes; i++) {
            pacientes[i].exibir();
        }
    }

    public void consultarProfissionaisCadastrados() {
        IO.println("Profissionais cadastrados:");
        if (totalProfissionais == 0) {
            IO.println("Nenhum profissional cadastrado.");
            return;
        }
        for (int i = 0; i < totalProfissionais; i++) {
            profissionais[i].exibir();
        }
    }
}