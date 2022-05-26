package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import interfaces.IRegistrazione;

public class RegistrazioneController extends DBController implements IRegistrazione{

    private static final String UTENTI_TABLE = "utenti";

    @Override
    public void registraUtente(String email, String password) {
        try {
            PreparedStatement statementRegistrazione = super.getDBConnection().prepareStatement("INSERT INTO " + UTENTI_TABLE + " (email, password) VALUES (?, ?)");
            
            statementRegistrazione.setString(1, email);
            statementRegistrazione.setString(2, password);

            statementRegistrazione.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
