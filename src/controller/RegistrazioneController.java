package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import interfaces.IRegistrazione;

public class RegistrazioneController extends DBController implements IRegistrazione{

    @Override
    public void registraUtente(String email, String password) {
        new GestioneGiocatoreController().aggiungiGiocatore(email, password);
    }
    
}
