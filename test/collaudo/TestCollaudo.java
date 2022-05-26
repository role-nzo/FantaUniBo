package collaudo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import beans.*;
import controller.ClassificheController;

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

        controllerClassifiche.aggiungiClassifica(classifica, "chiave");

        controllerClassifiche.partecipaClassifica("alfredo.dalcaldo@studio.unibo.it", classifica, "chiave");

        Classifica c = controllerClassifiche.ottieniClassifica(23);

        assertEquals(classifica, c);
    }

    @Test
    public void testConvalidaVotazioni(){

    }

    @Test
    public void testFunzioniAmministratore(){

    }

}