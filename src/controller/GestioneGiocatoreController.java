package controller;

import java.sql.Connection;

import interfaces.IGestioneGiocatore;

/**
 * GestioneGiocatoreController
 */
public class GestioneGiocatoreController extends DBController implements IGestioneGiocatore{

    @Override
    public int getPunteggio(String email) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void modificaEmail(String oldEmail, String newEmail) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Connection getDBConnection() {
        // TODO Auto-generated method stub
        return null;
    }

    
}