package beans;

import java.sql.Timestamp;
import java.util.Set;

import controller.VotazioniController;

public abstract class Votazione {

    private int id;
    private String descrizione;
    private Timestamp timestamp;
    private Set<Risposta> risposte;

    public Votazione() {
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

    public Set<Risposta> getRisposte() {
    	if(risposte == null)
    		risposte = new VotazioniController().ottieniRisposte(id);
        return risposte;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Timestamp getTimestamp() {
        return timestamp; 
    }
    
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    } 

    public abstract boolean isAmmesso(int valoreRisposta);

     
}
