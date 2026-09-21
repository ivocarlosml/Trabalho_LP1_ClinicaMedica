
class Paciente{

    private String nome;
    private String cpf;
    private Consulta[] consultas = new Consulta[Config.qtdConsultas];
    private int totalConsultas = 0;

    Paciente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    Consulta[] getConsultas() {
        return consultas;
    }

    int getTotalConsultas() {
        return totalConsultas;
    }

    void adicionarConsulta(Consulta consulta) {
        if (totalConsultas < consultas.length) {
            consultas[totalConsultas] = consulta;
            totalConsultas++;
        } else {
            IO.println("NÃO FOI POSSÍVEL ADICIONAR: LIMITE ATINGIDO!!!");
        }
    }

    void listarConsultas() {
        IO.println("Consultas do paciente " + nome + ":");
        if (totalConsultas == 0) {
            IO.println("Nenhuma consulta encontrada.");
            return;
        }
        for (int i = 0; i < totalConsultas; i++) {
            consultas[i].exibir();
        }
    }
    public void exibir() {
        IO.println("| Paciente: " + nome
                + " | CPF: " + cpf);
        }

}