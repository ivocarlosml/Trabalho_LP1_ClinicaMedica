import java.time.*;

class Consulta{
    private int id;
    private Paciente paciente;
    private Profissional profissional;
    private LocalDateTime dataHora;
    private StatusConsulta status;
    private String observacoes;

    Consulta(int id, Paciente paciente, Profissional profissional, LocalDateTime dataHora, String observacoes) {
        this.id = id;
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
        this.status = StatusConsulta.AGENDADA;
    }

        //Gets:
    int getId() {
        return id;
    }

    Paciente getPaciente() {
        return paciente;
    }

    Profissional getProfissional() {
        return profissional;
    }

    LocalDateTime getDataHora() {
        return dataHora;
    }

    StatusConsulta getStatus() {
        return status;
    }

    String getObservacoes() {
        return observacoes;
    }
    
    boolean cancelar() {
        if (status == StatusConsulta.REALIZADA) {
            IO.println("Não é possível cancelar: a consulta " + id + " já foi realizada.");
            return false;
        }
        status = StatusConsulta.CANCELADA;
        IO.println("Consulta " + id + " cancelada com sucesso.");
        return true;
    }

    boolean concluir() {
        if (status == StatusConsulta.CANCELADA) {
            IO.println("Não é possível concluir: a consulta " + id + " está cancelada.");
            return false;
        }
        status = StatusConsulta.REALIZADA;
        IO.println("Consulta " + id + " concluída com sucesso.");
        return true;
    }

    // Exibe os dados da consulta
    void exibir() {
        IO.println("Consulta [" + id + "] Paciente: " + paciente.getNome()
                + " | Profissional: " + profissional.getNome()
                + " | Data/Hora: " + dataHora
                + " | Status: " + status);
    }

}
