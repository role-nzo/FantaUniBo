package beans;

import java.time.LocalDateTime;

public class VotazioneAzioneSignificativa extends Votazione{

    private int CFU;
    private CorsoDiLaurea corsoDiLaurea;

    public VotazioneAzioneSignificativa() {}

    public VotazioneAzioneSignificativa(String descrizione, LocalDateTime timestamp, int CFU) {
        super(descrizione, timestamp);
        this.CFU = CFU;
    }

    @Override
    public boolean isAmmesso() {
        return false;
    }

    public int getCFU() {
        return CFU;
    }

    public void setCFU(int CFU) {
        this.CFU = CFU;
    }
    
    public CorsoDiLaurea getCorsoDiLaurea(){
        return this.corsoDiLaurea;
    }

    public void setCorsoDiLaurea(CorsoDiLaurea corsoDiLaurea){
        this.corsoDiLaurea = corsoDiLaurea;
    }

}
