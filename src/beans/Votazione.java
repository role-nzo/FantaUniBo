package beans;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public abstract class Votazione {

    private String descrizione;
    private LocalDateTime timestamp;

    public Votazione(String descrizione, LocalDateTime timestamp) {
        this.descrizione = descrizione;
        this.timestamp = timestamp;
    }
    
    public boolean Convalida(){
        return false;
    }
    
    public void rispondi(Risposta risposta){

    }

    public static Set<Votazione> getVotazioniGiornaliere(){
        Set<Votazione> votazioniGiornaliere = new HashSet<>();
        return votazioniGiornaliere;
    }

    public Set<Risposta> getRisposte(){
        Set<Risposta> risposte = new HashSet<>();
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

     //fare isAmmesso

     
}
