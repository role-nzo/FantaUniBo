package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import beans.Classifica;
import beans.ClassificaPrivata;
import beans.Giocatore;
import interfaces.IClassifiche;

public class ClassificheController extends DBController implements IClassifiche {

    private static final String CLASSIFICHE_TABLE = "classifichePrivate";
    private static final String GIOCATORI_CLASSIFICHE_TABLE = "giocatori_classifichePrivate";

    @Override
    public int aggiungiClassifica(ClassificaPrivata classifica, String chiave) {
        try {
            PreparedStatement statementClassifica = super.getDBConnection().prepareStatement("INSERT INTO " + CLASSIFICHE_TABLE + " (nome, hashChiave) VALUES (?, ?)");
            
            statementClassifica.setString(1, classifica.getNome());
            statementClassifica.setString(2, chiave);

            statementClassifica.executeUpdate();
            
            ResultSet generatedKeys = statementClassifica.getGeneratedKeys();
            
            if (generatedKeys.next()) {
                classifica.setId(generatedKeys.getInt(1));
                return classifica.getId();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    //FIXME: per i test
    public int eliminaClassifica(int idClassifica) {
        try {
            PreparedStatement statementClassifica = super.getDBConnection().prepareStatement("DELETE FROM " + CLASSIFICHE_TABLE + " WHERE id=?");
            
            statementClassifica.setInt(1, idClassifica);

            statementClassifica.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return -1;
    }

    @Override
    public void partecipaClassifica(int idGiocatore, ClassificaPrivata classifica, String chiave) {

        try {
            PreparedStatement statementClassifica = super.getDBConnection().prepareStatement("SELECT * FROM " + CLASSIFICHE_TABLE + " WHERE id=?");
            
            statementClassifica.setInt(1, classifica.getId());
            
            ResultSet resultClassifica = statementClassifica.executeQuery();
            
            if(resultClassifica.next()) {
                if(chiave.equals(resultClassifica.getString("hashChiave"))) {
                    PreparedStatement statementGiocatore = super.getDBConnection().prepareStatement("INSERT INTO " + GIOCATORI_CLASSIFICHE_TABLE + " (giocatore, classificaPrivata) VALUES (?,?)");

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
            PreparedStatement statementGiocatore = super.getDBConnection().prepareStatement("DELETE FROM " + GIOCATORI_CLASSIFICHE_TABLE + " WHERE giocatore=? AND classificaPrivata=?");

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
        Set<Giocatore> giocatori = new LinkedHashSet<Giocatore>();
        
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
