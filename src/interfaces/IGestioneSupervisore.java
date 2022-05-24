package interfaces;
import beans.*;
/**
 * IGestioneSupervisore
 */
public interface IGestioneSupervisore {

    public void aggiornaAzioneSignificativa(AzioneSignificativa azione);

    public void aggiornaEventoAvvenuto(EventoAvvenuto evento);
}