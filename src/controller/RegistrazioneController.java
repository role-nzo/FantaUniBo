package controller;

import java.sql.Connection;

import interfaces.IRegistrazione;

public class RegistrazioneController extends DBController implements IRegistrazione{

    @Override
    public void registraUtente(String email, String password) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Connection getDBConnection() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
