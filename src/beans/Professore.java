package beans;

import java.util.*;

public class Professore {

    private String nome;
    private String cognome;
    private String email;

    public Professore(String nome, String cognome, String email){
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
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

    public CorsoDiLaurea getCorsoDiLaurea(){
        return null;
    }

    public void setCorsiDiLaurea(Set<CorsoDiLaurea> corsiDiLAurea){

    }

    public Set<EventoAvvenuto> getEventiAvvenuti(){
        Set<EventoAvvenuto> eventiAvvenuti = new HashSet<>();
        return eventiAvvenuti;
    }

    public void aggiungiEventoAvvenuto(EventoAvvenuto eventoAvvenuto){
        
    }
    
}
