package beans;

import java.util.*;

public class CorsoDiLaurea {
    
    private int id;
    private String codice;
    private String annoAccademico;
    private String descrizione;
    private Set<Professore> professori;
    private Set<Giocatore> giocatori;
    private Universita universita;
    
    public CorsoDiLaurea() {
        this.professori = new HashSet<Professore>();
        this.giocatori = new HashSet<Giocatore>();
    }

    public CorsoDiLaurea(String codice, String annoAccademico, String descrizione) {
        this.codice = codice;
        this.annoAccademico = annoAccademico;
        this.descrizione = descrizione;
        this.professori = new HashSet<Professore>();
        this.giocatori = new HashSet<Giocatore>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Universita getUniversita(){
        return universita;
    }

    public void setUniversita(Universita universita){
        this.universita = universita;
    }

    public Set<Professore> getProfessori(){
        return professori;
    }

    public void setProfessori(Set<Professore> professori){
        this.professori = professori;
    }

    public Set<Giocatore> getGiocatori(){
        return giocatori;
    }

    public void aggiungiGiocatore(Giocatore giocatore){
        giocatori.add(giocatore);
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
