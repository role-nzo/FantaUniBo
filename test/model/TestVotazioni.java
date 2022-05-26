package model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import beans.*;

public class TestVotazioni {
 
    private VotazioneAzioneSignificativa votazAzioneGlobale;
    private VotazioneAzioneSignificativa votazAzioneIngInf;
    private VotazioneEventoAvvenuto votazEvento;
    private Professore professore;
    private CorsoDiLaurea ingInf;
    private AzioneSignificativa azioneSign;
    private Risposta risposta;
    private Giocatore giocatore;
    private LocalDateTime dataOra;

    @Before
    public void SetUp(){
        dataOra = LocalDateTime.now();
        // Creo una votazione per aggiungere una nuova azione significativa con visibilita' globale (tutti i corsi di laurea)
        votazAzioneGlobale = new VotazioneAzioneSignificativa("Il professore balla a lezione", dataOra);
        
         // Creo una votazione per aggiungere una nuova azione significativa con visibilita' al corso Ing. Inf.
        ingInf = new CorsoDiLaurea("12345","2021/22","Ingegneria Informatica");
        votazAzioneIngInf = new VotazioneAzioneSignificativa("Il professore arriva in ritardo a lezione", ingInf, dataOra);
        
        // Creo una votazione per validare un evento effettuato da un professore
        professore = new Professore("Pasquale", "Motivetto", "pasquale.motivetto@unibo.it");
        azioneSign = new AzioneSignificativa(500, ingInf,"Il professore canta a lezione");
        votazEvento = new VotazioneEventoAvvenuto("Votazione Pasquale ha cantato a lezione", professore, azioneSign, dataOra);

        // Creo una risposta alla votazione per azione significativa globale
        giocatore = new Giocatore("dario.lampa@studio.unibo.it");
        risposta = new Risposta(ValoreRisposta.SI.value, giocatore, votazAzioneGlobale);

    }

    @Test
    public void testGetter(){
        assertEquals("Il professore balla a lezione", votazAzioneGlobale.getDescrizione());
        assertEquals(true, votazAzioneGlobale.getCorsoDiLaurea().isEmpty());
        assertEquals(dataOra, votazAzioneGlobale.getTimestamp());

        assertEquals("Il professore arriva in ritardo a lezione", votazAzioneIngInf.getDescrizione());
        assertEquals(true, votazAzioneIngInf.getCorsoDiLaurea().isPresent());
        assertEquals(ingInf, votazAzioneIngInf.getCorsoDiLaurea().get());
        assertEquals(dataOra, votazAzioneIngInf.getTimestamp());

        assertEquals("Votazione Pasquale ha cantato a lezione", votazEvento.getDescrizione());
        assertEquals(azioneSign, votazEvento.getAzioneSignificativa());
        assertEquals(professore, votazEvento.getProfessore());
        assertEquals(dataOra, votazEvento.getTimestamp());
    }

    @Test
    public void testSetter(){

        // CHANGE? - I setter non ci sono perche' non avrebbe senso metterli, una volta che la votazione e' creata non si cambia

        votazAzioneGlobale.setDescrizione("Nuova descrizione per la votazione");
        assertEquals("Nuova descrizione per la votazione", votazAzioneGlobale.getDescrizione());

    }

    @Test
    public void testIsAmmesso(){
        assertEquals(true, votazAzioneGlobale.isAmmesso(ValoreRisposta.SI.value));
        assertEquals(false, votazAzioneGlobale.isAmmesso(ValoreRisposta.NONSO.value));

        assertEquals(true, votazEvento.isAmmesso(3));
        assertEquals(true, votazEvento.isAmmesso(ValoreRisposta.NONSO.value));
        assertEquals(false, votazEvento.isAmmesso(ValoreRisposta.SI.value));
    }

    @Test
    public void testRisposteOKandKO(){ 
        
        // Test OK - si puo rispondere alla votazione perche' il valore della risposta e' tra quelli ammessi

        votazAzioneGlobale.rispondi(risposta);
        Set<Risposta> risposte = new HashSet<Risposta>();
        risposte.add(risposta);

        assertEquals(risposte, votazAzioneGlobale.getRisposte());

        // Test KO - non si puo' rispondere ad una votazione per azioneSignificativa con una risposta relativa agli eventi avvenuti,
        // infatti isAmmesso == false in testIsAmmesso
        
        assertThrows( IllegalArgumentException.class, () -> { new Risposta(ValoreRisposta.NONSO.value, giocatore, votazAzioneGlobale); });
    }
    

}
