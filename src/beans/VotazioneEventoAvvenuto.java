package beans;

public class VotazioneEventoAvvenuto extends Votazione{

    private Professore professore;
    private AzioneSignificativa azioneSignificativa;

    public VotazioneEventoAvvenuto() {
        super();
    }
    
    public void setProfessore(Professore professore) {
        this.professore = professore;
    }
    
    public Professore getProfessore(){
        return professore;
    }
    
    public void setAzioneSignificativa(AzioneSignificativa azioneSignificativa){
        this.azioneSignificativa = azioneSignificativa;
    }

    public AzioneSignificativa getAzioneSignificativa(){
        return azioneSignificativa;
    }

    @Override
    public boolean isAmmesso(int valoreRisposta) {
        return valoreRisposta == ValoreRisposta.FALSO.value || valoreRisposta == ValoreRisposta.NONSO.value || valoreRisposta > 0;
    }
}
