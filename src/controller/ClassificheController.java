package controller;

import interfaces.IClassifiche;
import beans.*;

public class ClassificheController implements IClassifiche{

    @Override
    public void aggiungiClassifica(ClassificaPrivata classifica) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void partecipaClassifica(ClassificaPrivata classifica, String chiave) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void abbandonaClassifica(ClassificaPrivata classifica) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Classifica visualizzaClassifica(ClassificaPrivata classifica) {
        // TODO Auto-generated method stub
        // CHANGE - invece di accettare come parametro la ClassificaPrivata, mettere IDClassifica?
        return null;   
    }
    
}
