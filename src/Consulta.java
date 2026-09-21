import java.time.*;

class Consulta {

    private Paciente paciente;
    private Profissional profissional;
    private LocalDateTime dataHora;
    private StatusConsulta status;

    public Consulta(Paciente paciente, Profissional profissional, LocalDateTime dataHora) {
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataHora = dataHora;
        this.status = StatusConsulta.AGENDADA;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public StatusConsulta getStatus() {
        return status;
    }


    public boolean cancelar() {
        if (status == StatusConsulta.REALIZADA) {
            IO.println("Não é possível cancelar: a consulta já foi realizada!!!");
            return false;
        }
        status = StatusConsulta.CANCELADA;
        IO.println("Consulta cancelada com sucesso!!!");
        return true;
    }

    public boolean concluir() {
        if (status == StatusConsulta.CANCELADA) {
            IO.println("Não é possível concluir: a consulta foi cancelada!");
            return false;
        }
        status = StatusConsulta.REALIZADA;
        IO.println("Consulta concluída com sucesso!");
        return true;
    }

    public void exibir() {
        IO.println("------Consulta Médica------"
                + " | Paciente: " + paciente.getNome()
                + " | Profissional: " + profissional.getNome()
                + " | Data/Hora: " + dataHora
                + " | Status: " + status);
    }
}
