package beans;

public abstract class Classifica {
    
    protected String nome;

    public Classifica() {}

    public Classifica(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
}
