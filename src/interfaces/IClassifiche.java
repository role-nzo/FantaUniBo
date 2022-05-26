package interfaces;
import beans.*;
/**
 * IClassifiche
 */
public interface IClassifiche {

    public void aggiungiClassifica(ClassificaPrivata classifica, String chiave);

    public void partecipaClassifica(String email, ClassificaPrivata classifica, String chiave);

    public void abbandonaClassifica(String email, ClassificaPrivata classifica);

    public Classifica ottieniClassifica(int IDClassifica);
    
}