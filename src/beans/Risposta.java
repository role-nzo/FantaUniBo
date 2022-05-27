package beans;

import controller.VotazioniController;

public class Risposta {

	private int id;
    private int valore;
    private Giocatore giocatore = null;
    //FIXME: SERVE???
    private Votazione votazione = null;

    public Risposta() { }

    public int getValore() {
        return valore;
    }
    
    public void setValore(int valore) {
        this.valore = valore;
    }
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Giocatore getGiocatore() {
    	if(giocatore == null)
    		giocatore = new VotazioniController().ottieniGiocatoreDaRisposta(id);
        return giocatore;
    }
	
	public void setGiocatore(Giocatore giocatore) {
    	this.giocatore = giocatore;
    }

    public Votazione getVotazione(){
        return votazione;
    }
    
    public void setVotazione(Votazione votazione) {
        this.votazione = votazione;
    }

}
