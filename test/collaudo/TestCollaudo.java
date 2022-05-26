package collaudo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import beans.*;
import controller.ClassificheController;
import controller.GestioneAmministratoreController;
import controller.GestioneGiocatoreController;

public class TestCollaudo {
 
    private VotazioneAzioneSignificativa votazAzioneGlobale;
    private VotazioneAzioneSignificativa votazAzioneIngInf;
    private VotazioneEventoAvvenuto votazEvento;
    private Professore professore;
    private CorsoDiLaurea ingInf;
    private AzioneSignificativa azioneSign;
    private Risposta risposta;
    private Giocatore giocatore;
    private LocalDateTime dataOra;
    private Professore prof1;
    private Professore prof2;
    private Professore prof3;
    private Professore prof4;
    private Set<Professore> commissione;
    private Set<Professore> professoriSeguiti;
    private ClassificaPrivata classifica;


    @Before
    public void SetUp(){
        // Creo il giocatore
        giocatore = new Giocatore("alfredo.dalcaldo@studio.unibo.it");

        // Creo il corso del giocatore
        ingInf = new CorsoDiLaurea("12345","2021/22","Ingegneria Informatica");

        // Creo i professori che comporranno commissione e professoriSeguiti del giocatore
        prof1 = new Professore("Pino", "Silvestre", "pino.silvestre@unibo.it");
        prof2 = new Professore("Massimo", "Voltaggio", "massimo.voltaggio@unibo.it");
        prof3 = new Professore("Bianca", "Neve", "bianca.neve@unibo.it");
        prof4 = new Professore("Domenica", "D'agosto", "domenica.dagosto@unibo.it");

        // Affinche' commissione e professoriSeguiti siano validi, i professori devono insegnare (anche) nel corso del giocatore
        prof1.getCorsiDiLaurea().add(ingInf);
        prof2.getCorsiDiLaurea().add(ingInf);
        prof3.getCorsiDiLaurea().add(ingInf);
        prof4.getCorsiDiLaurea().add(ingInf);

        // Creo commissione e professori seguiti
        commissione = new HashSet<>();
        professoriSeguiti = new HashSet<>();

        commissione.add(prof1);
        commissione.add(prof2);
        commissione.add(prof3);
        commissione.add(prof4);

        professoriSeguiti.add(prof1);
        professoriSeguiti.add(prof3);
        professoriSeguiti.add(prof4);

        // Imposto i parametri del giocatore
        giocatore.setCorsoDiLaurea(ingInf);
        giocatore.setCommissione(commissione);
        giocatore.setProfessoriSeguiti(professoriSeguiti);

        //Creo una nuova classifica privata
        classifica = new ClassificaPrivata("Una nuova classifica");


    }

    @Test
    public void testClassifiche(){
        ClassificheController controllerClassifiche = new ClassificheController();
        GestioneGiocatoreController controllerGiocatore = new GestioneGiocatoreController();

        int idGiocatore = controllerGiocatore.aggiungiGiocatore("pippo@pluto.it", "pippo!");
        Giocatore giocatore = controllerGiocatore.ottieniGiocatore(idGiocatore);

        assertEquals("pippo@pluto.it", giocatore.getEmail());
        assertEquals(0, giocatore.getClassifichePrivate().size());

        ClassificaPrivata classifica = new ClassificaPrivata();
        classifica.setNome("I falliti");

        int idClassifica = controllerClassifiche.aggiungiClassifica(classifica, "ifallimentisiamonoi");

        controllerClassifiche.partecipaClassifica(idGiocatore, classifica, "ifallimenti(non)siamonoi");
        assertEquals(0, ((ClassificaPrivata) controllerClassifiche.ottieniClassifica(idClassifica)).getGiocatori().size());

        controllerClassifiche.partecipaClassifica(idGiocatore, classifica, "ifallimentisiamonoi");
        assertEquals(1, ((ClassificaPrivata) controllerClassifiche.ottieniClassifica(idClassifica)).getGiocatori().size());

        controllerClassifiche.abbandonaClassifica(idGiocatore, classifica);
        assertEquals(0, ((ClassificaPrivata) controllerClassifiche.ottieniClassifica(idClassifica)).getGiocatori().size());

        controllerClassifiche.abbandonaClassifica(idGiocatore, classifica);
        assertEquals(0, ((ClassificaPrivata) controllerClassifiche.ottieniClassifica(idClassifica)).getGiocatori().size());

        controllerClassifiche.eliminaClassifica(idClassifica);
        controllerGiocatore.eliminaGiocatore("pippo@pluto.it");
    }

    @Test
    public void testConvalidaVotazioni(){

    }

    @Test
    public void testFunzioniAmministratore(){
        GestioneAmministratoreController controllerAmministratore = new GestioneAmministratoreController();
        GestioneGiocatoreController controllerGiocatore = new GestioneGiocatoreController();

        int idUtente = controllerGiocatore.aggiungiGiocatore("pippo@pluto.it", "pippo!");
        Utente utente = controllerGiocatore.ottieniGiocatore(idUtente);

        assertEquals(Ruolo.PARTECIPANTE, utente.getRuolo());

        controllerAmministratore.aggiornaUtente(idUtente, Ruolo.AMMINISTRATORE);

        utente = controllerGiocatore.ottieniGiocatore(idUtente);

        assertEquals(Ruolo.AMMINISTRATORE, utente.getRuolo());

        //controllerGiocatore.eliminaGiocatore("pippo@pluto.it");
    }


    public static void main(String[] args) {
        new TestCollaudo().testFunzioniAmministratore();
    }
}