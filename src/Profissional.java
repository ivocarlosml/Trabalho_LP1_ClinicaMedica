import java.time.*;

class Profissional {

    private String nome;
    private String registroProfissional;

    private Especialidade especialidade;
    private Consulta[] consultas = new Consulta[Config.qtdConsultas]; // Variavel mantém limite de consultas
    private int totalConsultas = 0;

    public Profissional(String nome, String registroProfissional,Especialidade especialidade) {
        this.nome = nome;
        this.registroProfissional = registroProfissional;
        this.especialidade = especialidade;
    }

    public String getNome() {
        return nome;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public Consulta[] getConsultas() {
        return consultas;
    }

    public int getTotalConsultas() {
        return totalConsultas;
    }

    public void adicionarConsulta(Consulta consulta) {
        if (totalConsultas < consultas.length) {
            consultas[totalConsultas] = consulta;
            totalConsultas++;
        } else {
            IO.println("AGENDA DO PROFISSIONAL " + getNome() + " ESTÁ CHEIA!!!");
        }
    }

    public boolean possuiConsultaNoHorario(LocalDateTime dataHora) {
        for (int i = 0; i < totalConsultas; i++) {
            Consulta c = consultas[i];
            if (c.getDataHora().equals(dataHora) && c.getStatus() != StatusConsulta.CANCELADA) {
                return true;
            }
        }
        return false;
    }

    public void listarHorariosOcupados() {
        IO.println("Horários ocupados do profissional " + nome + ":");
        boolean encontrouAlgum = false;
        for (int i = 0; i < totalConsultas; i++) {
            Consulta c = consultas[i];
            if (c.getStatus() != StatusConsulta.CANCELADA) {
                IO.println(" - " + c.getDataHora());
                encontrouAlgum = true;
            }
        }
        if (!encontrouAlgum) {
            IO.println("Nenhum horário ocupado.");
        }
    }

    public void exibir() {
        IO.println("| Profissional: " + nome
                + " | Registro: " + registroProfissional
                + " | Especialidade: " + especialidade.getNome());
    }

    public boolean horarioDisponivel(LocalDateTime dataHora) {
        return !possuiConsultaNoHorario(dataHora);
    }
}