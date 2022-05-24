package beans;

import java.time.LocalDateTime;
import java.util.Optional;

public class VotazioneAzioneSignificativa extends Votazione{

    private Optional<CorsoDiLaurea> corsoDiLaurea;

    public VotazioneAzioneSignificativa(String descrizione, LocalDateTime timestamp) {
        super(descrizione, timestamp);
        corsoDiLaurea = Optional.empty();
    }

    public VotazioneAzioneSignificativa(String descrizione, CorsoDiLaurea corsoDiLaurea, LocalDateTime timestamp) {
        super(descrizione, timestamp);
        this.corsoDiLaurea = Optional.of(corsoDiLaurea);
    }

    public Optional<CorsoDiLaurea> getCorsoDiLaurea(){
        return corsoDiLaurea;
    }

    @Override
    public boolean isAmmesso(int valoreRisposta) {
        return valoreRisposta == ValoreRisposta.NO.value || valoreRisposta == ValoreRisposta.SI.value;
    }
    

}
