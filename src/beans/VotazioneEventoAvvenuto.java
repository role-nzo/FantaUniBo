package beans;

import java.time.LocalDateTime;

public class VotazioneEventoAvvenuto extends Votazione{

    public VotazioneEventoAvvenuto(String descrizione, LocalDateTime timestamp) {
        super(descrizione, timestamp);
    }
    
    public Professore getProfessore(){
        return null;
    }

    public AzioneSignificativa getAzioneSignificativa(){
        return null;
    }
}
