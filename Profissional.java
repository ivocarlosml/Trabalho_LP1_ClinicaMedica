import java.time.*;
class Profissional {
    private int id;
    private String nome;
    private String registroProfissional;
    private String telefone;
    private String email;
    private Especialidade especialidade;
    private Consulta[] consultas = new Consulta[Config.qtdConsultas];
    private int totalConsultas = 0;

    Profissional(int id, String nome, String registroProfissional, String telefone, Especialidade especialidade) {
        this.id = id;
        this.nome = nome;
        this.registroProfissional = registroProfissional;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    void adicionarConsulta(Consulta consulta) {
        if (totalConsultas < consultas.length) {
            consultas[totalConsultas] = consulta;
            totalConsultas++;
        } else {
        IO.println("AGENDA DO PROFISSIONAL "+ getNome() + "ESTÁ CHEIA!!!" );
        }
    }

    boolean possuiConsultaNoHorario(LocalDateTime dataHora) {
        for (int i = 0; i < totalConsultas; i++) {
            Consulta c = consultas[i];
            if (c.getDataHora().equals(dataHora) && c.getStatus() != StatusConsulta.CANCELADA) {
                return true;
            }
        }
        return false;
    }

    void listarHorariosOcupados() {
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

    void exibir() {
    IO.println("Profissional [" + id + "] " + nome
            + " | Registro: " + registroProfissional
            + " | Telefone: " + telefone
            + " | Especialidade: " + especialidade.getNome());
    }

    boolean horarioDisponivel(LocalDateTime dataHora) {
        return !possuiConsultaNoHorario(dataHora);
    }

    // Gets
    int getId() {
        return id;
    }

    String getNome() {
        return nome;
    }

    String getRegistroProfissional() {
        return registroProfissional;
    }

    String getTelefone() {
        return telefone;
    }

    Especialidade getEspecialidade() {
        return especialidade;
    }

    Consulta[] getConsultas() {
        return consultas;
    }

    int getTotalConsultas() {
        return totalConsultas;
    }
}