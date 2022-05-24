package controller;

import java.sql.Connection;

import interfaces.IGestioneGiocatore;

/**
 * GestioneGiocatoreController
 */
public class GestioneGiocatoreController extends DBController implements IGestioneGiocatore{

    @Override
    public int getPunteggio() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void modificaEmail(String newEmail) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Connection getDBConnection() {
        // TODO Auto-generated method stub
        return null;
    }

    
}