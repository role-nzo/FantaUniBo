package test.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import beans.*;

public class TestVotazioni {
 
    private Votazione azioneGlobale;
    private Votazione azioneIngInf;
    private Votazione evento;
    private Professore patella;
    private CorsoDiLaurea ingInf;
    private AzioneSignificativa cascato;

    @Before
    public void SetUp(){
        azioneGlobale = new VotazioneAzioneSignificativa("Votazione per azione globale", LocalDateTime.now());
        
        ingInf = new CorsoDiLaurea("12345","2021/22","Ingegneria Informatica");
        azioneIngInf = new VotazioneAzioneSignificativa("Votazione per azione ad Ing. Inf.", ingInf, LocalDateTime.now());
        
        patella = new Professore("Marco", "Patella", "suaemail@unibo.it");
        cascato = new AzioneSignificativa(100, ingInf,"Il prof casca a lezione");
        evento = new VotazioneEventoAvvenuto("Votazione Patella s'è cascato", patella, cascato, LocalDateTime.now() );
    }

    @Test
    public void testGetter(){
        assertEquals("Votazione per azione globale", azioneGlobale.getDescrizione());
        assertEquals("Votazione per azione ad Ing. Inf.", azioneIngInf.getDescrizione());
    }

    @Test
    public void testIsAmmesso(){
        assertEquals(true, azioneGlobale.isAmmesso(ValoreRisposta.SI.value));
        assertEquals(false, azioneGlobale.isAmmesso(ValoreRisposta.NONSO.value));

        assertEquals(true, evento.isAmmesso(3));
        assertEquals(true, evento.isAmmesso(ValoreRisposta.NONSO.value));
        assertEquals(false, evento.isAmmesso(ValoreRisposta.SI.value));
    }


}
