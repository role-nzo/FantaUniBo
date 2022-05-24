package beans;

import java.util.*;

public class Giocatore extends Utente{

    public Giocatore(String email){
        super(email);
    }

    public int getPunteggio(){
        return 0;
    }

    public Set<Professore> getProfessoriSeguiti(){
        Set<Professore> professoriSeguiti = new HashSet<>();
        return professoriSeguiti;
    }

    public void setProfessoriSeguiti(Set<Professore> ProfessoriSeguiti){

    }

    public Set<Professore> getCommissione(){
        Set<Professore> commissione = new HashSet<>();
        return commissione;
    }

    public void setCommissione(Set<Professore> Commissione){

    }

    public CorsoDiLaurea getCorsoDiLaurea(){
        return null;
    }

    public void setCorsoDiLaurea(CorsoDiLaurea corsoDiLaurea){

    }

    public Set<Classifica> getClassifichePrivate(){
        Set<Classifica> classifichePrivate = new HashSet<>();
        return classifichePrivate;
    }

    public void partecipaClassifica(Classifica classifica){

    }

    public void abbandonaClassifica(Classifica classifica){
        
    }
}
