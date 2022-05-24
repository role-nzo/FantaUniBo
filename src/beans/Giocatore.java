package beans;

import java.util.*;

public class Giocatore extends Utente{

    private int punteggio;
    private Set<Professore> commissione;
    private Set<Professore> professoriSeguiti;
    private CorsoDiLaurea corsoDiLaurea;
    private Set<Classifica> classifichePrivate;

    public Giocatore() {
        super();
    }

    public Giocatore(String email){
        super(email);
    }

    public int getPunteggio(){
        return punteggio;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public Set<Professore> getProfessoriSeguiti() {
        return professoriSeguiti;
    }

    public void setProfessoriSeguiti(Set<Professore> professoriSeguiti) {
        this.professoriSeguiti = professoriSeguiti;
    }

    public Set<Professore> getCommissione() {
        return commissione;
    }

    public void setCommissione(Set<Professore> commissione) {
        this.commissione = commissione;
    }

    public CorsoDiLaurea getCorsoDiLaurea() {
        return corsoDiLaurea;
    }

    public void setCorsoDiLaurea(CorsoDiLaurea corsoDiLaurea) {
        this.corsoDiLaurea = corsoDiLaurea;
    }

    public Set<Classifica> getClassifichePrivate() {
        return classifichePrivate;
    }

    public void partecipaClassifica(Classifica classifica) {
        classifichePrivate.add(classifica);
    }

    public void abbandonaClassifica(Classifica classifica){
        classifichePrivate.remove(classifica);
    }
}
