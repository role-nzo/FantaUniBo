package controller;

import java.sql.Connection;
import java.util.Set;

import beans.*;
import interfaces.IInserimentoDati;

public class InserimentoDatiController extends DBController implements IInserimentoDati{

    @Override
    public void inserisciCorsoDiLaurea(CorsoDiLaurea corso) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inserisciCommissione(Set<Professore> commissione) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void inserisciProfessoriSeguiti(Set<Professore> professoriSeguiti) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Connection getDBConnection() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
