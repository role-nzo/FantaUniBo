package controller;

import beans.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.Set;
import java.util.HashSet;

import interfaces.IGestioneGiocatore;

/**
 * GestioneGiocatoreController
 */
public class GestioneGiocatoreController extends DBController implements IGestioneGiocatore{

    private static final String UTENTI_TABLE = "utenti";
    private static final String GIOCATORI_TABLE = "giocatori";
    private static final String CORSI_DI_LAUREA_TABLE = "corsiDiLaurea";
    private static final String GIOCATORI_CLASSIFICHE_TABLE = "giocatori_classifichePrivate";
    private static final String GIOCATORI_COMMISSIONI_TABLE = "giocatori_commissioni";
    private static final String GIOCATORI_PROFESSORI_SEGUITI_TABLE = "giocatori_professoriSeguiti";
    private static final String PROFESSORI_TABLE = "professori";

    @Override
    public int getPunteggio(String email) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void modificaEmail(String oldEmail, String newEmail) {
        // TODO Auto-generated method stub
        
    }

    //FIXME: per i test
    public void eliminaGiocatore(String email) {
        try {
            PreparedStatement statementUtente = super.getDBConnection().prepareStatement("DELETE FROM " + UTENTI_TABLE + " WHERE email=?");
            
            statementUtente.setString(1, email);
            
            statementUtente.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int aggiungiGiocatore(String email, String password) {
        int id = -1;

        try {
            PreparedStatement statementUtente = super.getDBConnection().prepareStatement("Insert into " + UTENTI_TABLE + " (email, hashPassword, ruolo) VALUES (?, ?, 'partecipante')");
            PreparedStatement statementIDUtente = super.getDBConnection().prepareStatement("select * from " + UTENTI_TABLE + " WHERE email=?");
            PreparedStatement statementGiocatore = super.getDBConnection().prepareStatement("Insert into " + GIOCATORI_TABLE + " (idUtente) VALUES (?)");
            
            statementUtente.setString(1, email);
            statementUtente.setString(2, password);
            
            int row = statementUtente.executeUpdate();

            if(row > 0) {
                statementIDUtente.setString(1, email);
                ResultSet resultUtente = statementIDUtente.executeQuery();

                if(resultUtente.next()) {
                    id = resultUtente.getInt("id");

                    statementGiocatore.setInt(1, id);

                    statementGiocatore.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return id;
    }

    //FIXME: MA ottieniUtente?????
    @Override
    public Giocatore ottieniGiocatore(int id) {
        Giocatore giocatore = null;

        try {
            PreparedStatement statementGiocatore = super.getDBConnection().prepareStatement("Select * from " + UTENTI_TABLE + " AS A JOIN " + GIOCATORI_TABLE + " AS B ON A.id=B.idUtente where id=?");
            
            statementGiocatore.setInt(1, id);
            
            ResultSet resultGiocatore = statementGiocatore.executeQuery();

            if(resultGiocatore.next()) {
                giocatore = new Giocatore();
                giocatore.setId(resultGiocatore.getInt("id"));
                giocatore.setEmail(resultGiocatore.getString("email"));
                giocatore.setRuolo(Ruolo.from(resultGiocatore.getString("ruolo")));
                giocatore.setCorsoDiLaurea(ottieniCorsoDiLaurea(resultGiocatore.getInt("corsoDiLaurea")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return giocatore;
    }

    private CorsoDiLaurea ottieniCorsoDiLaurea(int id) {
        CorsoDiLaurea corsoDiLaurea = null;

        try {
            PreparedStatement statementCorsoDiLaurea = super.getDBConnection().prepareStatement("Select * from " + CORSI_DI_LAUREA_TABLE + " where id=?");
            
            statementCorsoDiLaurea.setInt(1, id);
            
            ResultSet resultCorsoDiLaurea = statementCorsoDiLaurea.executeQuery();

            if(resultCorsoDiLaurea.next()) {
                corsoDiLaurea = new CorsoDiLaurea();
                corsoDiLaurea.setId(resultCorsoDiLaurea.getInt("id"));
                corsoDiLaurea.setCodice(resultCorsoDiLaurea.getString("codice"));
                corsoDiLaurea.setAnnoAccademico(resultCorsoDiLaurea.getString("annoAccademico"));
                corsoDiLaurea.setDescrizione(resultCorsoDiLaurea.getString("descrizione"));
            }
        } catch (SQLException e) {

        }

        return corsoDiLaurea;
    }

    public Set<Professore> ottieniCommissione(int id) {
        Set<Professore> commissione = new HashSet<Professore>();

        try {
            PreparedStatement statementCommissione = super.getDBConnection().prepareStatement("Select * from " + GIOCATORI_COMMISSIONI_TABLE + " where giocatore=?");
            
            statementCommissione.setInt(1, id);
            
            ResultSet resultCommissione = statementCommissione.executeQuery();

            while(resultCommissione.next()) {
                commissione.add(ottieniProfessore(resultCommissione.getInt("professore")));
            }
        } catch (SQLException e) {

        }

        return commissione;
    }

    public Set<Professore> ottieniProfessoriSeguiti(int id) {
        Set<Professore> professoriSeguiti = new HashSet<Professore>();

        try {
            PreparedStatement statementProfessoriSeguiti = super.getDBConnection().prepareStatement("Select * from " + GIOCATORI_PROFESSORI_SEGUITI_TABLE + " where giocatore=?");
            
            statementProfessoriSeguiti.setInt(1, id);
            
            ResultSet resultProfessoriSeguiti = statementProfessoriSeguiti.executeQuery();

            while(resultProfessoriSeguiti.next()) {
                professoriSeguiti.add(ottieniProfessore(resultProfessoriSeguiti.getInt("professore")));
            }
        } catch (SQLException e) {

        }

        return professoriSeguiti;
    }

    public Professore ottieniProfessore(int id) {
        Professore professore = null;

        try {
            PreparedStatement statementProfessore = super.getDBConnection().prepareStatement("Select * from " + PROFESSORI_TABLE + " where giocatore=?");
            
            statementProfessore.setInt(1, id);
            
            ResultSet resultCommissione = statementProfessore.executeQuery();

            if(resultCommissione.next()) {
                professore = new Professore();
                professore.setId(resultCommissione.getInt("id"));
                professore.setNome(resultCommissione.getString("nome"));
                professore.setCognome(resultCommissione.getString("cognome"));
                professore.setEmail(resultCommissione.getString("email"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return professore;
    }

    public Set<Classifica> ottieniClassifiche(int id) {
        Set<Classifica> classifiche = new HashSet<Classifica>();
        ClassificheController cc = new ClassificheController();

        try {
            PreparedStatement statementClassifiche = super.getDBConnection().prepareStatement("Select * from " + GIOCATORI_CLASSIFICHE_TABLE + " where giocatore=?");
            
            statementClassifiche.setInt(1, id);
            
            ResultSet resultClassifiche = statementClassifiche.executeQuery();

            while(resultClassifiche.next()) {
                classifiche.add(cc.ottieniClassifica(resultClassifiche.getInt("classifica")));
            }
        } catch (SQLException e) {

        }

        return classifiche;
    }
    
    public static void main(String[] args) {

        //sono stato obbligato ad inserire gli id perché nelle tabelle di mapping i giocatori compaiono con i loro ID
        //  nelle query come "ottieniClassifica" è quindi necessario l'id e non la mail
        //  per ora ho inserito gli id solamente in Giocatore; sarà necessario farlo anche su tutte quelle entità con tabelle di mapping:
        //      professore, classifiche, eventiavvenuti, votazioni, azionisignificative, corsodilaurea


        //strategia possibile per il lazy load:
        //  caricare SOLO i tipi primitivi; tutte le entità composte (dotate quindi di classe) saranno caricate al bisogno
        //rivedere quindi CorsoDiLaurea: nel metodo "ottieniGiocatore" di questa classe è caricato automaticamente!!!

        //prime versione dei test

        GestioneGiocatoreController ggc = new GestioneGiocatoreController();

        //int id = ggc.aggiungiGiocatore("a@b.c", "password");

        Giocatore g = ggc.ottieniGiocatore(3);

        System.out.println(g.getEmail());
        System.out.println(g.getRuolo());
    }
}