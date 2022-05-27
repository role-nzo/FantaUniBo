package beans;

import java.util.*;
import controller.*;

public class Professore {

    private int id;
    private String nome;
    private String cognome;
    private String email;
    private Set<CorsoDiLaurea> corsiDiLaurea;
    private Set<EventoAvvenuto> eventiAvvenuti;

    public Professore() {}

    public Professore(String nome, String cognome, String email, Set<CorsoDiLaurea> corsiDiLaurea) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.corsiDiLaurea = new HashSet<CorsoDiLaurea>();
        this.eventiAvvenuti = new HashSet<EventoAvvenuto>();
    }

    public Professore(String nome, String cognome, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.corsiDiLaurea = new HashSet<CorsoDiLaurea>();
        this.eventiAvvenuti = new HashSet<EventoAvvenuto>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
    	if(eventiAvvenuti == null)
    		eventiAvvenuti = new GestioneGiocatoreController().ottieniEventiAvvenutiDaProfessore(id);
    	
        return eventiAvvenuti;
    }

    public void aggiungiEventoAvvenuto(EventoAvvenuto eventoAvvenuto){
        eventiAvvenuti.add(eventoAvvenuto);
    }

	public int getPunteggio() {
		int punteggio = 0;
		
		for(EventoAvvenuto e : getEventiAvvenuti()) {
			int media = 0;
			
			for(Integer valore : e.getValoriRisposteVincitrici()) {
				media += valore;
			}
			
			media /= e.getValoriRisposteVincitrici().size();
			
			punteggio += media * e.getAzioneSignificativa().getCFU();
		}
		
		return punteggio;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Professore) {
			if(((Professore) obj).getId() == getId())
				return true;
			else
				return false;
		} return false;
	}
	
	@Override
	public int hashCode() {
		return 123172 * this.id;
	}
    
}
