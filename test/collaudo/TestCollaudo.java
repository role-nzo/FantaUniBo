package collaudo;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import beans.*;
import controller.ClassificheController;
import controller.ConvalidaVotazioniController;
import controller.GestioneAmministratoreController;
import controller.GestioneGiocatoreController;
import controller.GestioneSupervisoreController;
import controller.VotazioniController;

public class TestCollaudo {
 
    private AzioneSignificativa azioneGlobale;

    private CorsoDiLaurea ingInf;

    private Giocatore giocatore1;
    private Giocatore giocatore2;

    private Professore prof1;
    private Professore prof2;
    private Professore prof3;
    private Professore prof4;

    private Set<Professore> commissione;
    private Set<Professore> professoriSeguiti;

    private ClassificaPrivata classifica;
    private EventoAvvenuto evento;


    @Before
    public void SetUp(){
        // Creo i giocatori
        giocatore1 = new Giocatore("alfredo.dalcaldo@studio.unibo.it");
        giocatore2 = new Giocatore("guido.piano@studio.unibo.it");

        // Creo il corso del giocatore
        ingInf = new CorsoDiLaurea("12345","2021/22","Ingegneria Informatica");

        // Creo i professori che comporranno commissione e professoriSeguiti del giocatore
        prof1 = new Professore("Pino", "Silvestre", "pino.silvestre@unibo.it");
        prof2 = new Professore("Massimo", "Voltaggio", "massimo.voltaggio@unibo.it");
        prof3 = new Professore("Bianca", "Neve", "bianca.neve@unibo.it");
        prof4 = new Professore("Domenica", "D'agosto", "domenica.dagosto@unibo.it");

        // Creo un evento avvenuto relativo al professore 1
        azioneGlobale = new AzioneSignificativa(-50, "Il professore arriva in ritardo di almeno 15 minuti");
        Set<Integer> risposteVincitrici = new HashSet<>();
        risposteVincitrici.add(ValoreRisposta.FALSO.value);
        evento = new EventoAvvenuto(new Date(), azioneGlobale, prof2, risposteVincitrici);
        prof2.getEventiAvvenuti().add(evento);
        
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

        // Imposto i parametri del giocatore 1
        giocatore1.setCorsoDiLaurea(ingInf);
        giocatore1.setCommissione(commissione);
        giocatore1.setProfessoriSeguiti(professoriSeguiti);

        // Imposto i parametri del giocatore 2
        giocatore2.setCorsoDiLaurea(ingInf);
        giocatore2.setCommissione(commissione);
        giocatore2.setProfessoriSeguiti(professoriSeguiti);

        //Creo una nuova classifica privata
        classifica = new ClassificaPrivata("I falliti");
    }

    @Test
    public void testClassifiche(){
        ClassificheController controllerClassifiche = new ClassificheController();
        GestioneGiocatoreController controllerGiocatore = new GestioneGiocatoreController();

        int idGiocatore = controllerGiocatore.aggiungiGiocatore("alfredo.dalcaldo@studio.unibo.it", "LaMiaPassword!");
        Giocatore giocatore = controllerGiocatore.ottieniGiocatore(idGiocatore);

        assertEquals("alfredo.dalcaldo@studio.unibo.it", giocatore.getEmail());
        assertEquals(0, giocatore.getClassifichePrivate().size());

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
        controllerGiocatore.eliminaGiocatore("alfredo.dalcaldo@studio.unibo.it");
    }

    @Test
    public void testConvalidaVotazioni(){
        // Creo votazione
        Votazione v1 = new VotazioneEventoAvvenuto("Oggi Pino e' arrivato in ritardo a lezione", prof1, azioneGlobale, LocalDateTime.now());
        
        VotazioniController votazController =  new VotazioniController(); //VotazioniController interagisce con il database
        votazController.aggiungiVotazione(v1);

        // Aggiungo due risposte di giocatori diversi alla votazione
        Risposta risposta = new Risposta(1, giocatore1, v1);
        votazController.aggiungiRisposta(risposta);
       
        risposta = new Risposta(1, giocatore2, v1);
        votazController.aggiungiRisposta(risposta);
        //La risposta verrà accettata con un quorum del 100% degli aventi diritto di voto

        // Simulo l'evento "finegiornata" con la relativa esecuzione di convalidaVotazioni
        new ConvalidaVotazioniController().convalidaVotazioni();

        // Il professore dovrebbe avere tra i suoi eventi avvenuti quello della votazione v1 appena convalidata
        new GestioneGiocatoreController().ottieniProfessore(prof1.getId());

        // Se esiste un evento avvenuto con data "oggi" relativo all'azione significativa della votazione v1,
        // allora e' stato creato e inserito correttamente
        assertEquals(true, prof1.getEventiAvvenuti().stream()
                                                    .anyMatch(e -> e.getAzioneSignificativa().equals(azioneGlobale) && e.getData().equals(new Date()))
                                                    );

    }

    @Test
    public void testFunzioniAmministratore(){
        GestioneAmministratoreController controllerAmministratore = new GestioneAmministratoreController();
        GestioneGiocatoreController controllerGiocatore = new GestioneGiocatoreController();

        int idUtente = controllerGiocatore.aggiungiGiocatore("lino.sasso@studio.unibo.it", "PiccolaRoccia!");
        Utente utente = controllerGiocatore.ottieniGiocatore(idUtente);

        assertEquals(Ruolo.PARTECIPANTE, utente.getRuolo());

        controllerAmministratore.aggiornaUtente(idUtente, Ruolo.AMMINISTRATORE);

        utente = controllerGiocatore.ottieniGiocatore(idUtente);

        assertEquals(Ruolo.AMMINISTRATORE, utente.getRuolo());

        //controllerGiocatore.eliminaGiocatore("pippo@pluto.it");
    }

    @Test
    public void testFunzioniSupervisore(){

        // modifica risultato di un evento avvenuto

        GestioneSupervisoreController controllerSupervisore = new GestioneSupervisoreController();

        evento = prof2.getEventiAvvenuti().iterator().next();
        int idEvento = evento.getId();

        Set<Integer> risposteVincitrici = new HashSet<>();
        risposteVincitrici.add(1);  //in SetUp e' stato creato un evento avvenuto relativo a prof2 con esito NONSO
        evento.setValoriRisposteVincitrici(risposteVincitrici);

        controllerSupervisore.aggiornaEventoAvvenuto(evento);

        EventoAvvenuto eventoNuovo = (EventoAvvenuto) new GestioneGiocatoreController()
                                                         .ottieniProfessore(prof2.getId())
                                                         .getEventiAvvenuti().stream()
                                                                             .filter(e -> e.getId() == idEvento).toArray()[0];
       
        assertEquals(risposteVincitrici, eventoNuovo.getValoriRisposteVincitrici());
        
    }
}