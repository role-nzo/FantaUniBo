package controller;

import interfaces.IClassifiche;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Set;

import java.util.HashSet;

import beans.*;

public class ClassificheController extends DBController implements IClassifiche {

    private static final String CLASSIFICHE_TABLE = "classifichePrivate";
    private static final String GIOCATORI_CLASSIFICHE_TABLE = "giocatori_classifichePrivate";

    @Override
    public void aggiungiClassifica(ClassificaPrivata classifica, String chiave) {
        try {
            PreparedStatement statementClassifica = super.getDBConnection().prepareStatement("INSERT INTO " + CLASSIFICHE_TABLE + " (nome, hashChiave) VALUES (?, ?)");
            
            statementClassifica.setString(1, classifica.getNome());
            statementClassifica.setString(2, chiave);
            
            statementClassifica.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void partecipaClassifica(int idGiocatore, ClassificaPrivata classifica, String chiave) {

        try {
            PreparedStatement statementClassifica = super.getDBConnection().prepareStatement("SELECT * FROM " + CLASSIFICHE_TABLE + " WHERE id=?");
            
            statementClassifica.setInt(1, classifica.getId());
            
            ResultSet resultClassifica = statementClassifica.executeQuery();

            if(resultClassifica.next()) {
                if(chiave == resultClassifica.getString("hashPassword")) {
                    PreparedStatement statementGiocatore = super.getDBConnection().prepareStatement("INSERTO INTO " + GIOCATORI_CLASSIFICHE_TABLE + "(giocatore, classificaPrivata) VALUES (?,?)");

                    statementGiocatore.setInt(1, idGiocatore);
                    statementGiocatore.setInt(2, classifica.getId());

                    statementGiocatore.executeUpdate();
                } 
                //TODO: aggiungere un ritorno se la chiave è sbagliata!!
                //else return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }

    @Override
    public void abbandonaClassifica(int idGiocatore, ClassificaPrivata classifica) {
        try {
            PreparedStatement statementGiocatore = super.getDBConnection().prepareStatement("DELETE FROM " + GIOCATORI_CLASSIFICHE_TABLE + "WHERE giocatore=? AND classificaPrivata=?");

            statementGiocatore.setInt(1, idGiocatore);
            statementGiocatore.setInt(2, classifica.getId());

            statementGiocatore.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        
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
                classifica.setId(resultClassifica.getInt("id"));
                classifica.setNome(resultClassifica.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println(e);
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
            System.out.println(e);
        }

        return giocatori;
    }
    
}
