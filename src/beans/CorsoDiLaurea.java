package beans;

import java.util.*;

public class CorsoDiLaurea {
    
    private String codice;
    private String annoAccademico;
    private String descrizione;
    
    public CorsoDiLaurea(String codice, String annoAccademico, String descrizione) {
        this.codice = codice;
        this.annoAccademico = annoAccademico;
        this.descrizione = descrizione;
    }

    public Universita getUniversita(){
        return null;
    }

    public void setUniversita(Universita universita){

    }

    public Set<Professore> getProfessori(){
        return null;
    }

    public void setProfessori(Set<Professore> professori){

    }

    public Set<Giocatore> getGiocatori(){
        Set<Giocatore> giocatori = new HashSet<>();
        return giocatori;
    }

    public void aggiungiGiocatore(Giocatore giocatore){
        
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getAnnoAccademico() {
        return annoAccademico;
    }

    public void setAnnoAccademico(String annoAccademico) {
        this.annoAccademico = annoAccademico;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    
}
