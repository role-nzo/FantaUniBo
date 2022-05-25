package beans;

import java.util.*;

public class Professore {

    private String nome;
    private String cognome;
    private String email;
    private Set<CorsoDiLaurea> corsiDiLaurea;
    private Set<EventoAvvenuto> eventiAvvenuti;

    public Professore(String nome, String cognome, String email){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.corsiDiLaurea = new HashSet<CorsoDiLaurea>();
        this.eventiAvvenuti = new HashSet<EventoAvvenuto>();
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCognome(){
        return this.cognome;
    }

    public void setCognome(String cognome){
        this.cognome = cognome;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public Set<CorsoDiLaurea> getCorsiDiLaurea(){
        return corsiDiLaurea;
    }

    public void setCorsiDiLaurea(Set<CorsoDiLaurea> corsiDiLaurea){
        this.corsiDiLaurea = corsiDiLaurea;
    }

    public Set<EventoAvvenuto> getEventiAvvenuti(){
        return eventiAvvenuti;
    }

    public void aggiungiEventoAvvenuto(EventoAvvenuto eventoAvvenuto){
        eventiAvvenuti.add(eventoAvvenuto);
    }
    
}
