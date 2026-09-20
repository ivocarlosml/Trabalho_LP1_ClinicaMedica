class Especialidade {
    private int id;
    private String nome;
    private String descricao;

    Especialidade(int id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    void exibir() {
        IO.println("Especialidade [" + id + "] " + nome + " - " + descricao);
    }

    int getId() {
        return id;
    }

    String getNome() {
        return nome;
    }

    String getDescricao() {
        return descricao;
    }
}