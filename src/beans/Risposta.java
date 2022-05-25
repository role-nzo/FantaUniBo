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

    /*
    CHANGE? Aggiungere i setter?

    public void setVotazione(Votazione votazione){
        this.votazione = votazione;
    }

    public void setGiocatore(Giocatore giocatore){
        this.giocatore = giocatore;
    }

    public void setValore(int valore){
        this.valore = valore;
    }
    */
}
