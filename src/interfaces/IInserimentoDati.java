package interfaces;

import java.util.Set;
import beans.*;

/**
 * InnerIInserimentoDati
 */
public interface IInserimentoDati {

    public void inserisciCorsoDiLaurea(String email, CorsoDiLaurea corso);

    public void inserisciCommissione(String email, Set<Professore> commissione);

    public void inserisciProfessoriSeguiti(String email, Set<Professore> professoriSeguiti);
}