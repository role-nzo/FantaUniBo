package interfaces;

import java.util.Set;
import beans.*;

/**
 * InnerIInserimentoDati
 */
public interface IInserimentoDati {

    public void inserisciCorsoDiLaurea(int idGiocatore, CorsoDiLaurea corso);

    public void inserisciCommissione(int idGiocatore, Set<Professore> commissione);

    public void inserisciProfessoriSeguiti(int idGiocatore, Set<Professore> professoriSeguiti);
}