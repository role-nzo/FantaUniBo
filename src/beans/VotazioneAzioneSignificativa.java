package beans;

import java.util.Optional;

public class VotazioneAzioneSignificativa extends Votazione{

    private Optional<CorsoDiLaurea> corsoDiLaurea;
    private int CFU;

    public VotazioneAzioneSignificativa() {
	}

	public Optional<CorsoDiLaurea> getCorsoDiLaurea(){
        return corsoDiLaurea;
    }

    public void setCorsoDiLaurea(Optional<CorsoDiLaurea> corsoDiLaurea){  //CHANGE? RIMUOVI?
        this.corsoDiLaurea = corsoDiLaurea;
    }

    public int getCFU(){
        return this.CFU;
    }

    public void setCFU(int CFU){
        this.CFU = CFU;
    }

    @Override
    public boolean isAmmesso(int valoreRisposta) {
        return valoreRisposta == ValoreRisposta.NO.value || valoreRisposta == ValoreRisposta.SI.value;
    }

}
