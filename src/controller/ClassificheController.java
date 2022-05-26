package controller;

import interfaces.IClassifiche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.ResultSet;
import java.util.Set;

import controller.DBController;

import java.util.HashSet;

import java.util.*;

import beans.*;

public class ClassificheController extends DBController implements IClassifiche {

    private static final String CLASSIFICHE_TABLE = "classifichePrivate";
    private static final String GIOCATORI_CLASSIFICHE_TABLE = "giocatori_classifichePrivate";
    private static final String CORSI_DI_LAUREA_TABLE = "corsiDiLaurea";

    @Override
    public void aggiungiClassifica(ClassificaPrivata classifica, String chiave) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void partecipaClassifica(String email, ClassificaPrivata classifica, String chiave) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void abbandonaClassifica(String email, ClassificaPrivata classifica) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Classifica ottieniClassifica(int IDClassifica) {
        ClassificaPrivata classifica = null;

        try {
            PreparedStatement statementClassifica = super.getDBConnection().prepareStatement("Select * from " + CLASSIFICHE_TABLE + " where id=?");
            
            statementClassifica.setInt(1, IDClassifica);
            
            ResultSet resultClassifica = statementClassifica.executeQuery();

            if(resultClassifica.next()) {
                classifica = new ClassificaPrivata();
                classifica.setNome(resultClassifica.getString("nome"));
            }
        } catch (SQLException e) {

        }

        return classifica;
    }

    public Set<Giocatore> ottieniGiocatori(int IDClassifica) {
        Set<Giocatore> giocatori = new HashSet<Giocatore>();

        try {
            PreparedStatement statementGiocatori = super.getDBConnection().prepareStatement("Select * from " + GIOCATORI_CLASSIFICHE_TABLE + " where classificaPrivata=?");
            
            statementGiocatori.setInt(1, IDClassifica);
            
            ResultSet resultGiocatori = statementGiocatori.executeQuery();

            while(resultGiocatori.next()) {
                Giocatore giocatore = new GestioneGiocatoreController().ottieniGiocatore(resultGiocatori.getInt("giocatore"));
                giocatori.add(giocatore);
            }
        } catch (SQLException e) {

        }

        return giocatori;
    }
    
}
