package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import beans.Ruolo;
import interfaces.ILogin;

public class LoginController extends DBController implements ILogin{

    private static final String UTENTI_TABLE = "utenti";

    @Override
    public Ruolo verificaCredenziali(String email, String password) { 
        try {
            PreparedStatement statementLogin = super.getDBConnection().prepareStatement("SELECT * FROM " + UTENTI_TABLE + " WHERE email=? AND hashPassword=?");
            
            statementLogin.setString(1, email);
            statementLogin.setString(2, password);

            ResultSet resultLogin = statementLogin.executeQuery();

            if(resultLogin.next()) {
                return Ruolo.from(resultLogin.getString("ruolo"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        } 

        return null;
    }

    @Override
    public boolean isUtenteBloccato(String email) {
        try {
            PreparedStatement statementBlocco = super.getDBConnection().prepareStatement("SELECT * FROM " + UTENTI_TABLE + " WHERE email=?");
            
            statementBlocco.setString(1, email);

            ResultSet resultLogin = statementBlocco.executeQuery();

            if(resultLogin.next()) {
                return resultLogin.getDate("scadenzaBlocco").after(new Date(System.currentTimeMillis()));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

    @Override
    public void bloccaUtente(String email) {
        try {
            PreparedStatement statementBlocco = super.getDBConnection().prepareStatement("UPDATE " + UTENTI_TABLE + " SET scadenzaBlocco=? WHERE email=?");
            
            statementBlocco.setString(1, email);
            statementBlocco.setDate(2, new Date(System.currentTimeMillis() + (15 * 60 * 1000)));

            statementBlocco.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
