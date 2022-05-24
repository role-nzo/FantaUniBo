package beans;

public class Risposta {

    private int valore;
    private Giocatore giocatore;
    private Votazione votazione;

    public Risposta(int valore) {
        this.valore = valore;
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
