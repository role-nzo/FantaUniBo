package interfaces;

import java.util.Set;
import beans.*;

/**
 * InnerIInserimentoDati
 */
public interface IInserimentoDati {

    public void inserisciCorsoDiLaurea(CorsoDiLaurea corso);

    public void inserisciCommissione(Set<Professore> commissione);

    public void inserisciProfessoriSeguiti(Set<Professore> professoriSeguiti);
}