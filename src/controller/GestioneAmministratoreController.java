package controller;

import beans.Ruolo;
import interfaces.IGestioneAmministratore;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Set;

public class GestioneAmministratoreController extends GestioneSupervisoreController implements IGestioneAmministratore{

    private static final String UTENTI_TABLE = "utenti";
    private static final String GIOCATORI_TABLE = "giocatori";
    private static final String CORSI_DI_LAUREA_TABLE = "corsiDiLaurea";
    private static final String GIOCATORI_CLASSIFICHE_TABLE = "giocatori_classifichePrivate";
    private static final String GIOCATORI_COMMISSIONI_TABLE = "giocatori_commissioni";
    private static final String GIOCATORI_PROFESSORI_SEGUITI_TABLE = "giocatori_professoriSeguiti";
    private static final String PROFESSORI_TABLE = "professori";

    @Override
    public void aggiornaUtente(int idUtente, Ruolo newRuolo) {
        //modifica il ruolo ma anche la riga nella tabella giocatori
        // impostando le foreign key e le cascade sul DB eliminando il giocatore dalla tabella giocatore
        // è sufficiente per eliminare tutte le sue informazioni
        try {
            PreparedStatement statementUtente = super.getDBConnection().prepareStatement("UPDATE " + UTENTI_TABLE + " SET ruolo=? where id=?");
            PreparedStatement statementGiocatore = super.getDBConnection().prepareStatement("DELETE FROM " + GIOCATORI_TABLE + " WHERE idUtente=?");
            
            statementUtente.setString(1, newRuolo.toString());
            statementUtente.setInt(2, idUtente);
            statementGiocatore.setInt(1, idUtente);
            
            statementUtente.executeUpdate();
            statementGiocatore.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
}
