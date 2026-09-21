class Especialidade {

    private String nome;
    private String descricao;

    public Especialidade(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public void exibir() {
        IO.println("Especialidade " + nome + " - " + descricao);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}