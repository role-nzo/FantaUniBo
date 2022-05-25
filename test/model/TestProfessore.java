package test.model;

import static org.junit.Assert.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import beans.*;

public class TestProfessore {
    
    private Professore professore;
    CorsoDiLaurea ingInf;
    AzioneSignificativa azioneGlobale;
    EventoAvvenuto evento;

    @Before
    public void SetUp(){
        azioneGlobale = new AzioneSignificativa(-50, "Il professore arriva in ritardo di almeno 15 minuti");
        ingInf = new CorsoDiLaurea("12345","2021/22","Ingegneria Informatica");
        
        professore = new Professore("Mario", "Rossi", "mario.rossi@unibo.it");
        professore.getCorsiDiLaurea().add(ingInf);

        Set<Integer> risposteVincitrici = new HashSet<>();
        risposteVincitrici.add(ValoreRisposta.FALSO.value);
        evento = new EventoAvvenuto(Date.valueOf( LocalDate.now() ), azioneGlobale , professore, risposteVincitrici);

        professore.aggiungiEventoAvvenuto(evento);
    }

    @Test
    public void testGetter(){
        assertEquals("Mario", professore.getNome());
        assertEquals("Rossi", professore.getCognome());
        assertEquals("mario.rossi@unibo.it", professore.getEmail());

        Set<CorsoDiLaurea> corsiDiMario = new HashSet<>();
        corsiDiMario.add(ingInf);
        assertEquals(corsiDiMario, professore.getCorsiDiLaurea());

        Set<EventoAvvenuto> eventiDiMario = new HashSet<>();
        eventiDiMario.add(evento);
        assertEquals(eventiDiMario, professore.getEventiAvvenuti());
    }

    @Test
    public void testSetter(){
        professore.setNome("Paolo");
        assertEquals("Paolo", professore.getNome());

        professore.setCognome("Bitta");
        assertEquals("Bitta", professore.getCognome());

        professore.setEmail("paolo.bitta@unibo.it");
        assertEquals("paolo.bitta@unibo.it", professore.getEmail());

        Set<CorsoDiLaurea> nuoviCorsiDiPaolo = new HashSet<>();
        CorsoDiLaurea dams = new CorsoDiLaurea("56789","2021/22","DAMS");
        
        nuoviCorsiDiPaolo.add(ingInf);
        nuoviCorsiDiPaolo.add(dams);
        professore.setCorsiDiLaurea(nuoviCorsiDiPaolo);
        assertEquals(nuoviCorsiDiPaolo, professore.getCorsiDiLaurea());
    }
}
