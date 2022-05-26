package beans;

import java.util.*;

public class EventoAvvenuto {
    
    private int id;
    private Date data;
    private Set<Integer> valoriRisposteVincitrici;
    private Professore professore;
    private AzioneSignificativa azioneSignificativa;

    public EventoAvvenuto() {
        this.valoriRisposteVincitrici = new HashSet<Integer>();
    }

    public EventoAvvenuto(Date data, AzioneSignificativa azione, Professore professore, Set<Integer> valoriRisposteVincitrici) {
        this.data = data;
        this.professore = professore;
        this.valoriRisposteVincitrici = valoriRisposteVincitrici;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AzioneSignificativa getAzioneSignificativa(){
        return azioneSignificativa;
    }

    public Professore getProfessore(){
        return professore;
    }

    public Date getData() {
        return data;
    }

    public Set<Integer> getValoriRisposteVincitrici() {
        return valoriRisposteVincitrici;
    }

    public void setValoriRisposteVincitrici(Set<Integer> valoriRisposteVincitrici) {
        this.valoriRisposteVincitrici = valoriRisposteVincitrici;
    }

    

    

}
