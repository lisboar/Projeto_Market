package model;

public class Cliente {
    private int id;
    private String nome, email;
    private boolean receberEmail;
    private Cidade cidade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isReceberEmail() {
        return receberEmail;
    }

    public void setReceberEmail(boolean receberEmail) {
        this.receberEmail = receberEmail;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
    
    
    
}
