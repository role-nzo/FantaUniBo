package controller;

import java.util.Set;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.*;
import interfaces.IInserimentoDati;

public class InserimentoDatiController extends DBController implements IInserimentoDati{

    private static final String GIOCATORI_TABLE = "giocatori";
    private static final String GIOCATORI_PROFESSORI_SEGUITI_TABLE = "giocatori_professoriSeguiti";
    private static final String GIOCATORI_COMMISSIONI_TABLE = "giocatori_commissioni";

    @Override
    public void inserisciCorsoDiLaurea(int idGiocatore, CorsoDiLaurea corso) {
        try {
            PreparedStatement statementGiocatore = super.getDBConnection().prepareStatement("UPDATE " + GIOCATORI_TABLE + " SET corsoDiLaurea=? WHERE id=?");
            
            statementGiocatore.setInt(1, corso.getId());
            statementGiocatore.setInt(2, idGiocatore);
            
            statementGiocatore.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void inserisciCommissione(int idGiocatore, Set<Professore> commissione) {
        try {
            PreparedStatement statementCommissione = super.getDBConnection().prepareStatement("INSERT INTO " + GIOCATORI_COMMISSIONI_TABLE + " (giocatore, professore) VALUES (?, ?)");

            for (Professore professore : commissione) {
                statementCommissione.setInt(1, idGiocatore);
                statementCommissione.setInt(2, professore.getId());
    
                //TODO: testare
                statementCommissione.addBatch();
            }
            
            statementCommissione.executeBatch();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void inserisciProfessoriSeguiti(int idGiocatore, Set<Professore> professoriSeguiti) {
        try {
            PreparedStatement statementProfessoriSeguiti = super.getDBConnection().prepareStatement("INSERT INTO " + GIOCATORI_PROFESSORI_SEGUITI_TABLE + " (giocatore, professore) VALUES (?, ?)");

            for (Professore professore : professoriSeguiti) {
                statementProfessoriSeguiti.setInt(1, idGiocatore);
                statementProfessoriSeguiti.setInt(2, professore.getId());
    
                //TODO: testare
                statementProfessoriSeguiti.addBatch();
            }
            
            statementProfessoriSeguiti.executeBatch();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
