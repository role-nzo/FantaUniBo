package beans;

public class Risposta {

    private int valore;
    private Giocatore giocatore;
    private Votazione votazione;

    public Risposta() { }

    public Risposta(int valore, Giocatore giocatore, Votazione votazione) {
        if(!votazione.isAmmesso(valore))
            throw new IllegalArgumentException();
        this.valore = valore;
        this.giocatore = giocatore;
        this.votazione = votazione;
    }

    public int getValore() {
        return valore;
    }

    public Giocatore getGiocatore(){
        return giocatore;
    }

    public Votazione getVotazione(){
        return votazione;
    }

}
