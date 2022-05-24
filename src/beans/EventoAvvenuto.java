package beans;

import java.util.*;

public class EventoAvvenuto {
    
    private Date data;
    private Set<Integer> valoriRisposteVincitrici;

    public EventoAvvenuto(Date data, Set<Integer> valoriRisposteVincitrici) {
        this.data = data;
        this.valoriRisposteVincitrici = valoriRisposteVincitrici;
    }

    public AzioneSignificativa getAzioneSignificativa(){
        return null;
    }

    public Professore getProfessore(){
        return null;
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
