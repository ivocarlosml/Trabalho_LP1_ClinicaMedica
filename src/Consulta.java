import java.time.*;
import java.time.format.DateTimeFormatter;

public class Consulta {

    private Paciente paciente;
    private Profissional profissional;
    private LocalDateTime dataHora;
    private StatusConsulta status;
    private String observacoes;

    public Consulta(Paciente paciente, Profissional profissional, LocalDateTime dataHora, String observacoes) {
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
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

    public String getObservacoes() {
        return observacoes;
    }

    public StatusConsulta getStatus() {
        return status;
    }


    public boolean cancelar() {
        if (status == StatusConsulta.REALIZADA) {
            IO.println("Não é possível cancelar: a consulta já foi realizada!!!");
            return false;
        }else if(status == StatusConsulta.CANCELADA){
            IO.println("\nConsulta já foi cancelada anteriormente!\n");
            return false;
        }
        status = StatusConsulta.CANCELADA;
        IO.println("Consulta cancelada com sucesso!!!");
        exibir();
        return true;
    }

    public boolean concluir() {
        if (status == StatusConsulta.CANCELADA) {
        IO.println("\nNão é possível concluir: a consulta foi cancelada!\n");
            return false;
        }else if(status == StatusConsulta.REALIZADA){
            IO.println("\nConsulta já foi realizada e concluida anteriormente!\n");
            return false;
        }
        else{
        status = StatusConsulta.REALIZADA;
        IO.println("\nConsulta concluída com sucesso!\n");
        exibir();
        return true;
        }
    }

    public void exibir() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        IO.println("\n| Paciente: " + paciente.getNome() +
                   "\n| Profissional: " + profissional.getNome() +
                   "\n| Data: " + dataHora.format(formatoData) +
                   "\n| Horário: " + dataHora.format(formatoHora) +
                   "\n| Observações: " + observacoes +
                   "\n| Consulta: " + status);
    }
}
