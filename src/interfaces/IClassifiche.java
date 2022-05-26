package interfaces;
import beans.*;
/**
 * IClassifiche
 */
public interface IClassifiche {

    public void aggiungiClassifica(ClassificaPrivata classifica, String chiave);

    //FIXME: ho messo id perché la mail non va bene
    public void partecipaClassifica(int idGiocatore, ClassificaPrivata classifica, String chiave);

    //FIXME: ho messo id perché la mail non va bene
    public void abbandonaClassifica(int idGiocatore, ClassificaPrivata classifica);

    public Classifica ottieniClassifica(int IDClassifica);
    
}