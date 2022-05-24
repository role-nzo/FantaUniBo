package interfaces;
import beans.*;
/**
 * IClassifiche
 */
public interface IClassifiche {

    public void aggiungiClassifica(ClassificaPrivata classifica);

    public void partecipaClassifica(ClassificaPrivata classifica, String chiave);

    public void abbandonaClassifica(ClassificaPrivata classifica);

    public void visualizzaClassifica(ClassificaPrivata classifica);
    
}