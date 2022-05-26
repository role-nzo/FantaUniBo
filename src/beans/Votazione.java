package beans;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Votazione {

    private int id;
    private String descrizione;
    private LocalDateTime timestamp;
    private Set<Risposta> risposte;

    public Votazione() {
        this.risposte = new HashSet<Risposta>();
    }

    public Votazione(String descrizione, LocalDateTime timestamp) {
        this.risposte = new HashSet<Risposta>();
        this.descrizione = descrizione;
        this.timestamp = timestamp;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean Convalida(){
        return false;
    }
    
    public void rispondi(Risposta risposta){
        risposte.add(risposta);
    }

    public static Set<Votazione> getVotazioniGiornaliere(){
        //TODO - interfacciamento con database? mettere nel controller?
        return null;
    }

    public Set<Risposta> getRisposte(){
        return risposte;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public abstract boolean isAmmesso(int valoreRisposta);

     
}
