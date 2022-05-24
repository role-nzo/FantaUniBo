package beans;

import java.time.LocalDateTime;

public class VotazioneEventoAvvenuto extends Votazione{

    private Professore professore;
    private AzioneSignificativa azioneSignificativa;

    public VotazioneEventoAvvenuto() {
        super();
    }

    public VotazioneEventoAvvenuto(String descrizione, Professore professore, AzioneSignificativa azioneSignificativa, LocalDateTime timestamp) {
        super(descrizione, timestamp);
        this.professore = professore;
        this.azioneSignificativa = azioneSignificativa;
    }
    
    public Professore getProfessore(){
        return professore;
    }

    public AzioneSignificativa getAzioneSignificativa(){
        return azioneSignificativa;
    }

    @Override
    public boolean isAmmesso(int valoreRisposta) {
        return valoreRisposta == ValoreRisposta.FALSO.value || valoreRisposta == ValoreRisposta.NONSO.value || valoreRisposta > 0;
    }
}
