package controller;

import java.sql.Connection;

import beans.Ruolo;
import interfaces.ILogin;

public class LoginController extends DBController implements ILogin{

    @Override
    public Ruolo verificaCredenziali(String email, String password) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isUtenteBloccato(String email) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void bloccaUtente(String email) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Connection getDBConnection() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
