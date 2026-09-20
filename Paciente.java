import java.time.*;
class Paciente{
    private int id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private String email;
    private String endereco;
    private Consulta[] consultas = new Consulta[Config.qtdConsultas];
    private int totalConsultas = 0;

    Paciente(int id, String nome, String cpf,LocalDate dataNascimento, String telefone, String email, String endereco) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco; 
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

    int getId() {
        return id;
    }

    String getNome() {
        return nome;
    }

    String getCpf() {
        return cpf;
    }

    String getTelefone() {
        return telefone;
    
    }
    LocalDate getdataNascimento(){
        return dataNascimento;
    }

    String getEmail() {
        return email;
    }

    String getEndereco(){
        return endereco;
    }


    Consulta[] getConsultas() {
        return consultas;
    }

    int getTotalConsultas() {
        return totalConsultas;
    }
}