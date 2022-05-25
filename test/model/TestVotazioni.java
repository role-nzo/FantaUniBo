package test.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import beans.*;

public class TestVotazioni {
 
    private VotazioneAzioneSignificativa azioneGlobale;
    private VotazioneAzioneSignificativa azioneIngInf;
    private VotazioneEventoAvvenuto evento;
    private Professore patella;
    private CorsoDiLaurea ingInf;
    private AzioneSignificativa cascato;
    private Risposta risposta;
    private Giocatore giocatore;
    private LocalDateTime dataOra;

    @Before
    public void SetUp(){
        dataOra = LocalDateTime.now();
        giocatore = new Giocatore();

        azioneGlobale = new VotazioneAzioneSignificativa("Votazione per azione globale", dataOra);
        
        ingInf = new CorsoDiLaurea("12345","2021/22","Ingegneria Informatica");
        azioneIngInf = new VotazioneAzioneSignificativa("Votazione per azione ad Ing. Inf.", ingInf, dataOra);
        
        patella = new Professore("Marco", "Patella", "suaemail@unibo.it");
        cascato = new AzioneSignificativa(100, ingInf,"Il prof casca a lezione");
        evento = new VotazioneEventoAvvenuto("Votazione Patella s'è cascato", patella, cascato, dataOra );

        risposta = new Risposta(ValoreRisposta.SI.value, giocatore, azioneGlobale);

    }

    @Test
    public void testGetter(){
        assertEquals("Votazione per azione globale", azioneGlobale.getDescrizione());
        assertEquals("Votazione Patella s'è cascato", evento.getDescrizione());

        assertEquals(dataOra, azioneGlobale.getTimestamp());

        assertEquals(true, azioneIngInf.getCorsoDiLaurea().isPresent());
        assertEquals(ingInf, azioneIngInf.getCorsoDiLaurea().get());

        assertEquals(true, azioneGlobale.getCorsoDiLaurea().isEmpty());
    }

    @Test
    public void testSetter(){

        azioneGlobale.setDescrizione("Nuova descrizione per la votazione");
        assertEquals("Nuova descrizione per la votazione", azioneGlobale.getDescrizione());

        

    }

    @Test
    public void testIsAmmesso(){
        assertEquals(true, azioneGlobale.isAmmesso(ValoreRisposta.SI.value));
        assertEquals(false, azioneGlobale.isAmmesso(ValoreRisposta.NONSO.value));

        assertEquals(true, evento.isAmmesso(3));
        assertEquals(true, evento.isAmmesso(ValoreRisposta.NONSO.value));
        assertEquals(false, evento.isAmmesso(ValoreRisposta.SI.value));
    }


    /*

    AGGIUNGERE?

    @Test
    public void testRispondi(){
        
        azioneGlobale.rispondi(risposta);
        Set<Risposta> risposte = new HashSet<Risposta>();
        risposte.add(risposta);

        assertEquals(risposte, azioneGlobale.getRisposte());
    }
    */

}
